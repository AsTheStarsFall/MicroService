package com.tianhy.springbootmall.service;


import com.tianhy.springbootmall.pojo.PurchaseRecordPo;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
public interface PurchaseService {
    /**
     * 处理购买业务
     * @param userId
     * @param productId
     * @param quantity
     * @return
     */
    public boolean purchase(Long userId,Long productId,int quantity);

    boolean purchaseRedis(Long userId, Long productId, int quantity);

    //将Redis的数据保存到数据库
    public boolean dealPurchaseRedis(List<PurchaseRecordPo> recordPoList);

}
