package com.tianhy.springbootmall.service.impl;

import com.tianhy.springbootmall.dao.ProductDao;
import com.tianhy.springbootmall.dao.PurchaseRecordDao;
import com.tianhy.springbootmall.pojo.ProductPo;
import com.tianhy.springbootmall.pojo.PurchaseRecordPo;
import com.tianhy.springbootmall.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PurchaseRecordDao purchaseRecordDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {

        //当前时间,通过时间戳限制，还可以用次数限制，比如循环3次
        long curr = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - curr > 100) {
                //如果循环时间大于100ms，终止循环
                return false;
            }
            //获取产品
            ProductPo product = productDao.getProduct(productId);
            //比较库存
            if (product.getStock() < quantity) {
                return false;
            }

            //获取版本号
            int version = product.getVersion();
            //扣减库存
            //同时将当前版本号与数据库版本号对比
//            int i = productDao.decreamentProduct(productId, quantity, version);
            int i = productDao.decreamentProduct(productId, quantity);
            //如果失败了，说明别的线程已经修改过
            if (i == 0) {
//                return false;
                // 导致失败，继续循环重试
                continue;
            }
            //构建购买记录
            PurchaseRecordPo purchaseRecord = createPurchaseRecord(userId, product, quantity);
            //插入购买记录
            purchaseRecordDao.insertPurchaseRecord(purchaseRecord);
            return true;
        }
    }

    public PurchaseRecordPo createPurchaseRecord(Long userId, ProductPo productPo, int quantity) {
        PurchaseRecordPo purchaseRecordPo = new PurchaseRecordPo();
        purchaseRecordPo.setUserId(userId);
        purchaseRecordPo.setNote("购买时间 ：" + System.currentTimeMillis());
        purchaseRecordPo.setPrice(productPo.getPrice());
        purchaseRecordPo.setProductId(productPo.getId());
        purchaseRecordPo.setQuantity(quantity);
        double total = productPo.getPrice() * quantity;
        purchaseRecordPo.setTotal(total);
        return purchaseRecordPo;
    }

    //Lua脚本执行
    String purchaseScript =
            //将产品编号保存到集合
            "redis.call('sadd',KEYS[1],ARGV[2]) \n"
                    //购买列表
                    + "local productPurchaseList = KEYS[2]..ARGV[2] \n"
                    // 用户编号
                    + "local userId = ARGV[1] \n"
                    // 产品key
                    + "local product = 'product_'..ARGV[2] \n"
                    // 购买数量
                    + "local quantity = tonumber(ARGV[3]) \n"
                    // 当前库存
                    + "local stock = tonumber(redis.call('hget', product, 'stock')) \n"
                    // 价格
                    + "local price = tonumber(redis.call('hget', product, 'price')) \n"
                    // 购买时间
                    + "local purchase_date = ARGV[4] \n"
                    // 库存不足，返回0
                    + "if stock < quantity then return 0 end \n"
                    // 减库存
                    + "stock = stock - quantity \n"
                    + "redis.call('hset', product, 'stock', tostring(stock)) \n"
                    // 计算价格
                    + "local sum = price * quantity \n"
                    // 合并购买记录数据
                    + "local purchaseRecord = userId..','..quantity..','"
                    + "..sum..','..price..','..purchase_date \n"
                    // 保存到将购买记录保存到list里
                    + "redis.call('rpush', productPurchaseList, purchaseRecord) \n"
                    // 返回成功
                    + "return 1 \n";

    //购买记录前缀
    private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";
    //抢购产品集合
    private static final String PRODUCT_SCHEDULE = "product_schedule_set";
    //32位 SHA1码,第一次执行先让Redis进行缓存脚本返回
    private String sha1 = null;

    @Override
    public boolean purchaseRedis(Long userId, Long productId, int quantity) {
        //购买时间
        Long purchaseDate = System.currentTimeMillis();

        Jedis jedis = null;
        try {
            //获取底层连接
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(purchaseScript);
            }
            //执行脚本，返回结果
            Object res = jedis.evalsha(sha1, 2, PRODUCT_SCHEDULE, PURCHASE_PRODUCT_LIST,
                    userId + "", productId + "", quantity + "", purchaseDate + "");
            Long result = (Long) res;
            return result == 1;
        } finally {
            //关闭连接
            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
    }

    //将Redis的数据保存到数据库
    @Override
    //另起一个事务,如果出现异常回滚，只会回滚这个方法内部的事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean dealPurchaseRedis(List<PurchaseRecordPo> recordPoList) {
        for (PurchaseRecordPo recordPo : recordPoList) {
            purchaseRecordDao.insertPurchaseRecord(recordPo);
            productDao.decreamentProduct(recordPo.getProductId(), recordPo.getQuantity());
        }

        return true;
    }
}
