package com.tianhy.format;

import com.alibaba.fastjson.JSON;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 4:46
 **/
public class JsonFormatProcessor implements FormatProcessor{
    @Override
    public <T> String doFormat(T obj) {
        return "JsonFormatProcessor:"+ JSON.toJSONString(obj);
    }
}
