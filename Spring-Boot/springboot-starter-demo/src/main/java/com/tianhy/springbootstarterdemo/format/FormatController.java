package com.tianhy.springbootstarterdemo.format;

import com.tianhy.FormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 5:49
 **/
@RestController
public class FormatController {

    @Autowired
    FormatTemplate formatTemplate;

    @RequestMapping("/format")
    public String format(){

        User user = new User();
        user.setAge(18);
        user.setName("tianhy");
        return formatTemplate.doFormat(user);
    }

}
