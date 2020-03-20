package com.tianhy.springbootmall;

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
public class HttpCallable implements Callable<String> {

    private String url;

    private String jsonParams;

    public HttpCallable(String url, String jsonParams) {
        this.url = url;
        this.jsonParams = jsonParams;
    }

    /**
     * 执行并返回结果
     */
    @Override
    public String call() throws Exception {
        return HttpTool.requestPost(url, jsonParams);
    }

    /**
     * 模拟并发测试
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //模拟并发数
        int concurrencyNumber = 30000;

        List<String> resList = Lists.newArrayList();

        //执行线程池
        ExecutorService ex = Executors.newFixedThreadPool(100);

        String url = "http://localhost/purchase";
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", 1);
        map.put("productId", 1);
        map.put("quantity", 1);

        for (int i = 0; i < concurrencyNumber; i++) {
            Future<String> callRes = ex.submit(new HttpCallable(url, JSON.toJSONString(map)));
            resList.add(i + ">>>" + callRes.get());
        }

//        for (String s : resList) {
//            System.out.println("------>" + s);
//        }
    }
}
