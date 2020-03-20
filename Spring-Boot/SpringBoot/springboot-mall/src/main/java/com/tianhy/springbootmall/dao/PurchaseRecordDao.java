package com.tianhy.springbootmall.dao;

import com.tianhy.springbootmall.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@Repository
@Mapper
public interface PurchaseRecordDao {
    public int insertPurchaseRecord(PurchaseRecordPo purchaseRecordPo);
}
