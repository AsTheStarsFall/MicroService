package com.tianhy.springbootspringmvc.validator;

import com.tianhy.springbootspringmvc.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * {@link}
 *
 * @Desc: 需要注解InitBinder 绑定
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        //支持User类
        return clazz.equals(User.class);
    }

    /**
     * @param target 参数绑定后的Pojo
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (target == null) {
            errors.rejectValue("", null, "用户信息为空");
            return;
        }

        User user = (User) target;
        // 用户名非空串
        if (StringUtils.isEmpty(user.getUserName())) {
            // 增加错误，可以进入控制器方法
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
