package com.tianhy.study.service;

import com.tianhy.study.config.HelloFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: thy
 * @Date: 2020/2/22 3:47
 * @Desc:
 */
@FeignClient(name = "github-client", url = "https://api.github.com", configuration = HelloFeignConfig.class)
public interface HelloService {

    /**
     * github查询api格式：
     * repository_search_url:"https://api.github.com/search/repositories?q={query}{&page,per_page,sort,order}"
     *
     * @param queryStr
     * @return
     */

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String searchRepo(@RequestParam("q") String queryStr);
}
