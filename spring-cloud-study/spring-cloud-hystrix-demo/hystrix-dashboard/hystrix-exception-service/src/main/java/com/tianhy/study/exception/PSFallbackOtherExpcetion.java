package com.tianhy.study.exception;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @Author: thy
 * @Date: 2020/2/23 21:34
 * @Desc:
 */
public class PSFallbackOtherExpcetion extends HystrixCommand<String> {

    public PSFallbackOtherExpcetion() {
        super(HystrixCommandGroupKey.Factory.asKey("GroupOE"));
    }

    @Override
    protected String run() throws Exception {
        throw new Exception("PSFallbackOtherExpcetion error");
    }

    @Override
    protected String getFallback() {
        System.out.println(getFailedExecutionException().getMessage());
        return "invoke PSFallbackOtherExpcetion fallback method:  ";
    }
}
