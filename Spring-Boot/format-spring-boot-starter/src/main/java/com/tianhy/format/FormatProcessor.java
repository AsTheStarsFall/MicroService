package com.tianhy.format;

/**
 * {@link}
 *
 * @Desc: 格式转换接口
 * @Author: thy
 * @CreateTime: 2019/10/3 4:45
 **/
public interface FormatProcessor {
    <T> String doFormat(T obj);
}
