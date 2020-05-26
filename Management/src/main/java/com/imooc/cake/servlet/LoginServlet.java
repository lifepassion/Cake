package com.imooc.cake.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if("/login.do".equals(req.getServletPath())){
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            //如果用户名密码相等且不为null执行登陆操作
            if(Objects.equals(username,password)&&username!=""&&password!=""){
                req.getSession().setAttribute("username",username);
                req.getRequestDispatcher("/cake/list.do").forward(req,resp);
            }else {
                req.getRequestDispatcher("/loginPrompt.do").forward(req,resp);
            }
        }else if("/loginPrompt.do".equals(req.getServletPath())){
            req.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(req,resp);
        }

    }
}
