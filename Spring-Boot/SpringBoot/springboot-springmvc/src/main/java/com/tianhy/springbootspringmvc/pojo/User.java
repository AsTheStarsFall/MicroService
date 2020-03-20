package com.tianhy.springbootspringmvc.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Data
@Alias("user")
public class User implements Serializable {
    private Long id;
    private String userName;
    private String note;
}
