package com.aaa.ssm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * className:LoginFilter
 * discription:登录过滤器
 * author:fhm
 * createTime:2018-12-10 11:09
 */
@WebFilter(filterName="loginFilter",urlPatterns="/jump/*")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;
        Object username= request.getSession().getAttribute("userName");
        String requestURI = ((HttpServletRequest) arg0).getRequestURI();
        if(requestURI.contains("/jump/register1")||requestURI.contains("/jump/list")||requestURI.contains("/jump/borrow")||requestURI.contains("/jump/personal")){
            if(username==null){
                //跳转到登录页面
                request.getRequestDispatcher("/jump/login").forward(arg0,arg1);
            }
        }
        arg2.doFilter(arg0, arg1);

    }

    @Override
    public void destroy() {

    }
}
