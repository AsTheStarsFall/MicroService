package com.tianhy.springbootc5jdbc.pojo;

import com.tianhy.springbootc5jdbc.enumeration.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Data
@Entity(name = "user")
@Table(name = "t_user")
@Alias(value = "user") //Mybatis指定别名
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_name")
    private String userName = null;

    private String note =null;

//    @Convert(converter = SexConvert.class)
    //可以通过Mybatis的typeHandler来处理
    private SexEnum sex = null;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + userName + '\'' +
                ", note='" + note + '\'' +
                ", sex=" + sex +
                '}';
    }
}
