package com.tianhy.springbootmongodb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
//标识为MongoDB文档
@Document
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7895435231819517614L;

    @Id
    private Long id;

    @Field("user_name")
    private String userName =null;

    private String note =null;

    //角色列表
    private List<Role> roles = null;
}
