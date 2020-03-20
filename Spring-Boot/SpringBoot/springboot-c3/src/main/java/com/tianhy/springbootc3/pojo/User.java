package com.tianhy.springbootc3.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Data
/**
 * @ComponentScan 用哪种策略扫描装配
 * @Component 表明哪个类被扫描到IOC
 * user 作为Bean的名称，IOC会将类名的首字母小写，Value是具体的值
 */
@Component("user")
public class User implements Serializable {
    @Value("1")
    private Long id;
    @Value("user_name_1")
    private String userName;
    @Value("note_1")
    private String note;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
