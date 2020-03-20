package com.tianhy.study.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sc-hello-service")
public interface HelloService {
	
	@RequestMapping(value = "/helloService", method = RequestMethod.GET)
    public String getHelloServiceData();
}
