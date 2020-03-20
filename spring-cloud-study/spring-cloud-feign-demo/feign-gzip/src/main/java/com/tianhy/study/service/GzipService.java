package com.tianhy.study.service;

import com.tianhy.study.config.GzipFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: thy
 * @Date: 2020/2/22 3:47
 * @Desc:
 */
@FeignClient(name = "gzip-feign", url = "https://api.github.com", configuration = GzipFeignConfig.class)
public interface GzipService {

    /**
     * github查询api格式：
     * repository_search_url:"https://api.github.com/search/repositories?q={query}{&page,per_page,sort,order}"
     *
     * @param queryStr
     * @return
     */

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String queryStr);
}
