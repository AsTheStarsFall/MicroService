package com.tianhy.springbootmall.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
public class Result {

    private int code;
    private String msg;

    private Map<String, Object> results = new HashMap<>();

    public static Result success() {
        Result result = new Result();
        result.setCode(100);
        result.setMsg("购买成功");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("购买失败，库存不足");
        return result;
    }

    public Result add(String info, Object val) {
        this.getResults().put(info, val);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }
}
