package com.tianhy.springbootsecurity.pojo;

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
@Alias("dbuser")
public class DataBaseUser implements Serializable {
    private Long id;
    private String userName;
    private String pwd;
    private Boolean available;
    private String note;
}
