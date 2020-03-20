package com.tianhy.springbootmall.service.impl;

import com.tianhy.springbootmall.pojo.PurchaseRecordPo;
import com.tianhy.springbootmall.service.PurchaseService;
import com.tianhy.springbootmall.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PurchaseService purchaseService;

    //购买记录集合前缀
    private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";
    //抢购产品集合
    private static final String PRODUCT_SCHEDULE = "product_schedule_set";

    //每次取出一千条
    private static final int ONCE_SIZE = 1000;

    @Override
    //每天凌晨1点
//    @Scheduled(cron = "0 0 1 * * ?x")
    //每分钟执行一次
    @Scheduled(fixedRate = 1000 * 20)
    public void purchaseTask() {
        System.out.println("定时任务开始");
        //返回抢购产品集合
        Set<String> productList = stringRedisTemplate.opsForSet().members(PRODUCT_SCHEDULE);
        //
        List<PurchaseRecordPo> purchaseRecordPoList = new ArrayList<>();
        //遍历抢购产品
        for (String s : productList) {
            Long productId = Long.parseLong(s);
            //购买记录前缀
            String purchaseKey = PURCHASE_PRODUCT_LIST + productId;
            //绑定键
            BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps(purchaseKey);
            //计算记录数
            Long size = stringRedisTemplate.opsForList().size(purchaseKey);

            //次数
            Long times = size % ONCE_SIZE == 0 ? size / ONCE_SIZE : size / ONCE_SIZE + 1;
            for (int i = 0; i < times; i++) {
                List<String> proList = null;
                if (i == 0) {
                    //获取 i*ONCE_SIZE ~ (i+1)*ONCE_SIZE 区间的元素
                    proList = listOps.range(i * ONCE_SIZE, (i + 1) * ONCE_SIZE);
                } else {
                    proList = listOps.range(i * ONCE_SIZE + 1, (i + 1) * ONCE_SIZE);
                }
                //遍历Redis中购买记录的元素
                for (String s1 : proList) {
                    //转换成POJO实体
                    PurchaseRecordPo purchaseRecordPo = createPurchaseRecordPo(productId, s1);
                    purchaseRecordPoList.add(purchaseRecordPo);
                }
                try {
                    //保存到数据库,会新建事务
                    purchaseService.dealPurchaseRedis(purchaseRecordPoList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //清空，继续循环写入
                purchaseRecordPoList.clear();
            }
            //删除购买记录
            stringRedisTemplate.delete(purchaseKey);
            //删除抢购产品
            stringRedisTemplate.opsForSet().remove(PRODUCT_SCHEDULE, s);
        }

        System.out.println("定时任务结束");
    }

    /**
     * 根据Redis 数据创建购买记录的实体
     *
     * @param id
     * @param proStr
     * @return
     */
    private PurchaseRecordPo createPurchaseRecordPo(Long id, String proStr) {
        PurchaseRecordPo purchaseRecordPo = new PurchaseRecordPo();
        String[] strings = proStr.split(",");

        //按照元素下标顺序获取对应的值
        //"local purchaseRecord = userId..','..quantity..','..sum..','..price..','..purchase_date \n"
        Long userId = Long.parseLong(strings[0]);
        int quantity = Integer.parseInt(strings[1]);
        double sum = Double.valueOf(strings[2]);
        double price = Double.valueOf(strings[3]);
        Long time = Long.valueOf(strings[4]);
        Timestamp timestamp = new Timestamp(time);
        purchaseRecordPo.setProductId(id);
        purchaseRecordPo.setPurchaseTime(timestamp);
        purchaseRecordPo.setPrice(price);
        purchaseRecordPo.setQuantity(quantity);
        purchaseRecordPo.setTotal(sum);
        purchaseRecordPo.setUserId(userId);
        purchaseRecordPo.setNote("购买日志，时间：" + timestamp.getTime());
        return purchaseRecordPo;
    }
}
