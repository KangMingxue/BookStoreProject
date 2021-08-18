package com.atguigu.servlet.model;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 11:19
 */
public class ToIndexServlet extends ViewBaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //查询所有图书信息
            List<Book> allBooks = bookService.getAllBooks();
            //将数据共享域中
            request.setAttribute("bookList",allBooks);
            //跳转首页
            processTemplate("index",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("跳转index.html有误！！！");
        }
    }
}
