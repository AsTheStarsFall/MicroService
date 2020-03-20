package com.tianhy.study.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @Author: thy
 * @Date: 2020/2/23 21:55
 * @Desc: 对异常信息的包装
 */
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            //包装400~500之间的异常
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader());
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
