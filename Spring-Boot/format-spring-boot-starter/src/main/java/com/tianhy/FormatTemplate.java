package com.tianhy;

import com.tianhy.autoconfiguration.FormatPropterties;
import com.tianhy.format.FormatProcessor;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 5:20
 **/
public class FormatTemplate {

    private FormatProcessor formatProcessor;
    private FormatPropterties formatPropterties;

    public FormatTemplate(FormatProcessor formatProcessor, FormatPropterties formatPropterties) {
        this.formatProcessor = formatProcessor;
        this.formatPropterties = formatPropterties;
    }

    public <T> String doFormat(T obj){
        StringBuilder sb = new StringBuilder();
        sb.append("formatProperties:").append(formatProcessor.doFormat(formatPropterties.getInfo())).append("<br/>");
        sb.append("begin doFormat:"+"<br/>");
        sb.append("result:").append(formatProcessor.doFormat(obj)).append("<br/>");
        return sb.toString();
    }
}
