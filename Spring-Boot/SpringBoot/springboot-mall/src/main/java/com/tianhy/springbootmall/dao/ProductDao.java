package com.tianhy.springbootmall.dao;

import com.tianhy.springbootmall.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface ProductDao {
    public ProductPo getProduct(Long id);

    //减库存
    public int decreamentProduct(@Param("id") Long id, @Param("quantity") int quantity);

}

