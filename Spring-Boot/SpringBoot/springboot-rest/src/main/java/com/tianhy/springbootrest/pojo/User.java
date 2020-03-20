package com.tianhy.springbootrest.pojo;

import com.tianhy.springbootrest.enumeration.SexEnum;
import com.tianhy.springbootrest.vo.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Data
@Alias(value = "user") //Mybatis指定别名
public class User extends UserVo implements Serializable {

    private Long id;

    private String userName;

    private String note;

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
