package com.tianhy.springclouduser.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@Data
public class UserPo implements Serializable {
    private Long id;

    private String userName;

    private int level;

    private String note;
}
