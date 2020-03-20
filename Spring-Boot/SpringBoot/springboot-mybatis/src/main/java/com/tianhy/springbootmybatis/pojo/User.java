package com.tianhy.springbootmybatis.pojo;

import com.tianhy.springbootmybatis.enumeration.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.nutz.dao.entity.annotation.Column;

import java.io.Serializable;


/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Alias(value = "user") //Mybatis指定别名
public class User implements Serializable {

    private Long id = null;

    private String userName = null;

    private String note = null;

    //可以通过Mybatis的typeHandler来处理
    private SexEnum sex = null;

    public User(Long id, String userName, String note, SexEnum sex) {
        this.id = id;
        this.userName = userName;
        this.note = note;
        this.sex = sex;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                ", sex=" + sex +
                '}';
    }
}
