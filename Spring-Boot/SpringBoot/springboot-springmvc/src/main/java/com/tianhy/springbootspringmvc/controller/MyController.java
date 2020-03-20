package com.tianhy.springbootspringmvc.controller;

import com.tianhy.springbootspringmvc.pojo.ValidatorPojo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@RequestMapping("/my")
@Controller
public class MyController {

    @RequestMapping("/valid/page")
    public String validPage() {
        return "/validator/pojo";
    }

    @RequestMapping("/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo vp, Errors errors) {
        Map<String, Object> errmap = new HashMap<>();
        List<ObjectError> allErrors = errors.getAllErrors();
        for (ObjectError oe : allErrors) {
            String key = null;
            String msg = null;
            //字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();
            } else {
                //非字段错误
                key = oe.getObjectName();
            }
            //错误信息
            msg = oe.getDefaultMessage();

            errmap.put(key, msg);

        }

        return errmap;
    }
}
