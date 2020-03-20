package com.tianhy.springboot.domain;

import com.tianhy.springboot.validation.constraints.ValidatorCardNumber;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/13
 **/
@Data
public class User {

    @Max(value = 10000)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @ValidatorCardNumber
    private String cardNum;
}
