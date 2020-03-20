package com.tianhy.thirddemo.current;

import com.tianhy.thirddemo.other.OtherConfig;
import org.springframework.context.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 13:14
 **/
//引入其他类
@Import(OtherConfig.class)
@Configuration
public class ThirdSpringConfig {

    @Bean
    public ThirdBean thirdBean(){
        return new ThirdBean();
    }
}
