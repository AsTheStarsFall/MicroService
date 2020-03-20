package com.tianhy.springbootmongodb.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Document
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -7895435231819517614L;

    private Long id;
    @Field("role_name")
    private String roleName =null;
    private String note=null;
}
