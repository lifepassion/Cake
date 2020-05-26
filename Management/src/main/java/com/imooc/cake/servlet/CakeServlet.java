package com.imooc.cake.servlet;

import com.imooc.cake.entity.Cake;
import com.imooc.cake.entity.Category;
import com.imooc.cake.service.CakeService;
import com.imooc.cake.service.CategoryService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * 蛋糕的Servlet
 *
 * @author passionlife
 */
public class CakeServlet extends HttpServlet {

    CakeService cakeService;
    CategoryService categoryService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathName = req.getServletPath();
        System.out.println(pathName);
        if ("/cake/list.do".equals(pathName)) {
            String categoryIdStr = req.getParameter("categoryId");
            Long categoryId = null;
            if (null != categoryIdStr) {
                categoryId = Long.valueOf(categoryIdStr);
                List<Cake> cakes = cakeService.getCakesByCategoryId(categoryId, 1, 500);
                List<Category> categories = categoryService.getCategories();
                req.setAttribute("cakes", cakes);
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/WEB-INF/views/biz/cake_list.jsp").forward(req, resp);
            } else {
                List<Cake> cakes = cakeService.getCakes(1, 500);
                List<Category> categories = categoryService.getCategories();
                req.setAttribute("cakes", cakes);
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/WEB-INF/views/biz/cake_list.jsp").forward(req, resp);
            }

        } else if ("/cake/addPrompt.do".equals(req.getServletPath())) {
            List<Category> categories = categoryService.getCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/add_cake.jsp").forward(req, resp);
        } else if("/cake/add.do".equals(pathName)){
            req.setCharacterEncoding("utf-8");
            if (ServletFileUpload.isMultipartContent(req)) {
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    //解析请求
                    List<FileItem> items = upload.parseRequest(req);
                    Iterator<FileItem> ite = items.iterator();
                    Cake cake = new Cake();
                    while (ite.hasNext()) {
                        FileItem item = ite.next();
                        //信息是普通的格式
                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            if ("categoryId".equals(fieldName)) {
                                cake.setCategoryId(Long.valueOf(item.getString()));
                            } else if ("level".equals(fieldName)) {
                                cake.setLevel(Integer.valueOf(item.getString()));
                            } else if ("name".equals(fieldName)) {
                                cake.setName(new String(item.getString().getBytes("iso8859-1"), "utf-8"));
                            } else if ("price".equals(fieldName)) {
                                cake.setPrice(Integer.valueOf(item.getString()));
                            }
                        } else {
                            //信息是文件格式
                            cake.setSmallImg(item.get());
                        }
                    }
                    cakeService.addCake(cake);
                    req.getRequestDispatcher("/cake/list.do").forward(req, resp);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void init() throws ServletException {
        super.init();
        cakeService = new CakeService();
        categoryService = new CategoryService();
    }

    @Override
    public void destroy() {
        super.destroy();
        cakeService = null;
        categoryService = null;
    }


}
