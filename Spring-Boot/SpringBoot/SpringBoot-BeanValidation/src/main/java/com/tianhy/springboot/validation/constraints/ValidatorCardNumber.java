package com.tianhy.springboot.validation.constraints;

import com.tianhy.springboot.validation.CardNumberConstraintsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/13
 **/
@Documented
@Constraint(validatedBy = { CardNumberConstraintsValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidatorCardNumber {

    String message() default "{com.tianhy.bean.validation.invalid.cardnumber.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
