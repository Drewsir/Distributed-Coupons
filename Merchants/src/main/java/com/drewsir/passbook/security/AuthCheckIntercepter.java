package com.drewsir.passbook.security;

import com.drewsir.passbook.constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>权限拦截器</h1>
 * 完成权限校验，拦截所有的 http 请求
 * @author drew
 */
@Component
public class AuthCheckIntercepter implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //在 HTTP 请求真正被处理前，做前置处理
        String token = httpServletRequest.getHeader(Constants.TOKEN_STRING);

        // HTTP 请求为携带 token，不能通过校验
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Header 中缺少 " + Constants.TOKEN_STRING + "!");
        }

        //非法请求，颁发的 token 和 传入的 token 不一致
        if (!token.equals(Constants.TOKEN)) {
            throw new Exception("Header 中 " + Constants.TOKEN_STRING + "错误!");
        }

        // token 正确，将 token 设置到 threadlocal 中，方便获取, 并返回 true
        //本项目中所有商户都用同一个 token，但企业开发中每个商户对应不同的 token
        AccessContext.setToken(token);

        return true;
    }

    //在 HTTP 请求真正被处理后
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //确定 http 请求执行完之后一定会执行此方法，常用来清除信息，由于在 controller 中可能会出现错误导致 postHandle 不会执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //抛出异常时，postHandle 不会处理，确定 http 请求之后，才会真正执行 --> 所以会做一些清理工作
        AccessContext.clearAccessKey();//清除线程中的 token 信息
    }
}
