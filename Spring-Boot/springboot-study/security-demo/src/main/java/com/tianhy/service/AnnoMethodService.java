package com.tianhy.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc: 开启了注解安全配置后，配合使用注解
 * @Author: thy
 * @CreateTime: 2020/3/5 1:08
 **/
@Service
public class AnnoMethodService {

    @Secured("ROLE_ADMIN")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    public String dba(){
        return "dba";
    }


    @PreAuthorize("hasAnyRole('ADMIN','DBA','USER')")
    public String user(){
        return "user";
    }

}
