package com.tianhy.springbootrest.exception;

import lombok.Data;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Data
public class NotFoundExeception extends RuntimeException {

    private static final long serialVersionUID = 1L;
    // 异常编码
    private Long code;
    // 异常自定义信息
    private String customMsg;

    public NotFoundExeception(Long code, String customMsg) {
        super();
        this.code = code;
        this.customMsg = customMsg;
    }

    public NotFoundExeception() {
    }
}
