package com.tianhy.format;

import java.util.Objects;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 5:05
 **/
public class StringFormatProcessor implements FormatProcessor {
    @Override
    public <T> String doFormat(T obj) {
        return "StringFormatProcessor:" + Objects.toString(obj);
    }
}
