package com.tianhy.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

/**
 * {@link}
 *
 * @Desc: 网关过滤器
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@Component
public class MyZuulFilter extends ZuulFilter {

    //注入Jedis模板
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //请求前
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器排序，数字越小优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否过滤
    @Override
    public boolean shouldFilter() {
        //请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取HttpServletRequest对象
        HttpServletRequest request = ctx.getRequest();
        //取出表单序列号
        String serialNumber = request.getParameter("serialNumber");
        //如果存在验证码，返回true，启用过滤器
        return !StringUtils.isEmpty(serialNumber);
    }

    //过滤逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取HttpServletRequest对象
        HttpServletRequest request = ctx.getRequest();
        String serialNumber = request.getParameter("serialNumber");
        String verificationCode = request.getParameter("verificationCode");
        //从Redis取出验证码
        String vcode = stringRedisTemplate.opsForValue().get(serialNumber);

        //当验证码为空或者与表单的验证码不匹配时，拦截请求报出错误
        if (vcode == null || !vcode.equals(verificationCode)) {
            //不再转发请求
            ctx.setSendZuulResponse(false);
            //设置HTTP响应码401（未授权）
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
            //设置响应体
            ctx.setResponseBody("{'success': false,message:'Verification Code error!'}");
        }
        //一致放过
        return null;
    }
}
