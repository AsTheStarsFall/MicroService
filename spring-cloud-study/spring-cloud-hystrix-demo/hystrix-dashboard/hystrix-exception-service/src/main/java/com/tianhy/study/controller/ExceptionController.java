package com.tianhy.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tianhy.study.exception.PSFallbackBadRequestExpcetion;
import com.tianhy.study.exception.PSFallbackOtherExpcetion;
import com.tianhy.study.exception.ProviderServiceCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: thy
 * @Date: 2020/2/23 21:48
 * @Desc:
 */
@RestController
public class ExceptionController {


    @GetMapping("/getProviderServiceCommand")
    public String providerServiceCommand() {
        String result = new ProviderServiceCommand("World").execute();
        return result;
    }

    //测试 PSFallbackBadRequestExpcetion 异常
    @GetMapping("/getPSFallbackBadRequestExpcetion")
    public String psFallbackBadRequestExpcetion() {
        String result = new PSFallbackBadRequestExpcetion().execute();
        return result;
    }


    //测试 PSFallbackOtherExpcetion 异常
    @GetMapping("/getPSFallbackOtherException")
    public String psFallbackOtherException() {
        String result = new PSFallbackOtherExpcetion().execute();
        return result;
    }

    //在@HystrixCommand 中获取异常信息，在fallback参数带Throwable
    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getFallbackMethod(String id) {
        throw new RuntimeException("getFallbackMethodTest failed");
    }

    public String fallback(String id, Throwable throwable) {
        System.err.println(throwable.getMessage());
        return "this fallback message ";
    }


}
