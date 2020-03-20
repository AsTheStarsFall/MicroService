package com.tianhy.springbootmall.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@Data
@Alias("product")
public class ProductPo implements Serializable {
    private static final long serialVersionUID = 3288311147760635602L;
    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;
}
