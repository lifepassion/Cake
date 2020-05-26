package com.imooc.cake.servlet;

import com.imooc.cake.entity.Category;
import com.imooc.cake.service.CakeService;
import com.imooc.cake.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    CategoryService categoryService;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathName=req.getServletPath();
        if("/category/list.do".equals(pathName)){
            List<Category> categories = categoryService.getCategories();
            req.setAttribute("categories",categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/category_list.jsp").forward(req,resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        categoryService=null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        categoryService=new CategoryService();
    }
}
