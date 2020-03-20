package com.tianhy.springbootrest.vo;

import lombok.Data;

/**
 * {@link}
 *
 * @Desc: VO (View Object) 视图对象
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Data
public class UserVo {
    private Long id;
    private String userName;
    private String note;
    private int sexCode;
    private String sexName;

    public UserVo(Long id, String userName, String note, int sexCode, String sexName) {
        this.id = id;
        this.userName = userName;
        this.sexCode = sexCode;
        this.sexName = sexName;
        this.note = note;
    }

    public UserVo() {
    }
}
