package com.tianhy.springbootmall.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@Data
@Alias("purchaseRecord")
public class PurchaseRecordPo implements Serializable {
    private static final long serialVersionUID = -360816189433370174L;
    private Long id;
    private Long userId;
    private Long productId;
    private double price;
    private int quantity;
    private double total;
    private Timestamp purchaseTime;
    private String note;
}
