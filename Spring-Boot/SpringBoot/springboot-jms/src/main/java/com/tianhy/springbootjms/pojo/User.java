package com.tianhy.springbootjms.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 8081849731640304905L;

    private Long id;
    private String userName = null;
    private String note = null;

    public User(Long id, String userName, String note) {
        this.id = id;
        this.userName = userName;
        this.note = note;
    }
}
