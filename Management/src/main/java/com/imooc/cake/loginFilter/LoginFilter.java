package com.imooc.cake.loginFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String pathName=((HttpServletRequest)servletRequest).getServletPath();
        Object username=((HttpServletRequest)servletRequest).getSession().getAttribute("username");
        if("/login.do".equals(pathName)||"/loginPrompt.do".equals(pathName)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if(null!=username){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            servletRequest.getRequestDispatcher("/loginPrompt.do").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
