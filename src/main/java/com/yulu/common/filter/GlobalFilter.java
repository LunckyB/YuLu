package com.yulu.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GlobalFilter extends OncePerRequestFilter {

    // 不需要token的url
    private static final List<String> NOT_FILTER_URI = new ArrayList<>();
    // 没有token且需要签名的url
    private static final List<String> SIGN_URL = new ArrayList<>();

    static {
        NOT_FILTER_URI.add("/api/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // 数据允许是否允许被其他源进行获取 *: 任意源
        response.setHeader("Access-Control-Allow-Methods", "GET, POST"); // 支持的请求方式, 逗号隔开
        response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,SessionToken,Cookie"); // 请求中可以使用的那些header字段符合请求头规范的字符串
        response.setHeader("Access-Control-Max-Age", "3600"); // 标识预检请求的有效期，单位秒， 0表示每次都发起预检请求
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否可以将对请求的响应暴露给页面。true: 可以  false: 不可以
        response.setHeader("Access-Control-Expose-Headers", "*"); //
        request.setCharacterEncoding("UTF-8");

        System.out.println("filter===============");

        filterChain.doFilter(request, response);
    }
}
