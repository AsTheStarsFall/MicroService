package com.tianhy.springboot.validation;

import com.tianhy.springboot.validation.constraints.ValidatorCardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/13
 **/
public class CardNumberConstraintsValidator implements ConstraintValidator<ValidatorCardNumber, String> {

    //初始化
    @Override
    public void initialize(ValidatorCardNumber constraintAnnotation) {

    }

    /**
     * @Description: 规则：前缀必须以 THY- 开头，后缀必须以数字结尾
     * @Param: [value, context]
     * @return: boolean
     * @Author: thy
     * @Date: 2019/4/13
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        //以 - 分为前半部分和后半部分
        String[] split = StringUtils.split(value,"-");

        if(ArrayUtils.getLength(split) != 2){
            return false;
        }
        String prefix = split[0];
        String suffix = split[1];

        boolean pre = Objects.equals(prefix, "THY");
        boolean suf = StringUtils.isNumeric(suffix);

        return pre && suf;
    }
}
