package com.tianhy.study.exception;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @Author: thy
 * @Date: 2020/2/23 21:34
 * @Desc:
 */
public class PSFallbackBadRequestExpcetion extends HystrixCommand<String> {

    public PSFallbackBadRequestExpcetion() {
        super(HystrixCommandGroupKey.Factory.asKey("GroupBRE"));
    }

    //抛出HystrixBadRequestException不会被fallback
    //这种异常一把对应的是由非法参数或者一些非系统异常引起的
    @Override
    protected String run() throws Exception {
        throw new HystrixBadRequestException("HystrixBadRequestException error");
    }

    @Override
    protected String getFallback() {
        System.out.println(getFailedExecutionException().getMessage());
        return "invoke HystrixBadRequestException fallback method:  ";
    }
}
