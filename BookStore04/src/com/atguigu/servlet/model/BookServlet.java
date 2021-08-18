package com.atguigu.servlet.model;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 15:23
 */
public class BookServlet extends ModelBaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 跳转添加book页面
     */

    protected void toAddBookPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("manager/book_edit",request,response);
    }
    /**
     * 查询所有book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //2. 调用service
            List<Book> bookList = bookService.getAllBooks();
            //3. 将数据共享域中
            request.setAttribute("bookList",bookList);
            //4. 路径跳转
            processTemplate("manager/book_manager",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("查询book信息有误，"+e.getMessage());
        }

    }

    /**
     * 删除book信息【通过bookId】
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            String bookId = request.getParameter("bookId");
            //2. 调用service
            bookService.deleteBookById(bookId);
            //3. 将数据共享域中
            //4. 路径跳转
            response.sendRedirect(request.getContextPath()+"/BookServlet?method=getAllBooks");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("删除book有误，"+e.getMessage());
        }

    }

    /**
     * 跳转修改页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            String bookId = request.getParameter("bookId");
            //2. 调用service
            Book book = bookService.getBookByBookId(bookId);
            //3. 将数据共享域中
            request.setAttribute("book",book);
            //4. 路径跳转【book_edit.html】
            processTemplate("manager/book_edit",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("修改book有误，"+e.getMessage());
        }

    }

    /**
     * 添加book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void saveBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数【获取所有参数】
            Map<String, String[]> parameterMap = request.getParameterMap();
            Book book = new Book();
            BeanUtils.populate(book,parameterMap);
            //2. 调用service
            bookService.saveBook(book);
            //4. 路径跳转【添加成功后，重新查询并跳转指定页面显示数据】
//            getAllBooks(request,response);
            response.sendRedirect(request.getContextPath()+"/BookServlet?method=getAllBooks");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("添加book信息有误，"+e.getMessage());
        }

    }

    /**
     * 修改book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            Book book = new Book();
            BeanUtils.populate(book,parameterMap);
            System.out.println("book = " + book);
            //2. 调用service
            bookService.updateBook(book);
            //3. 将数据共享域中
            response.sendRedirect(request.getContextPath()+"/BookServlet?method=getAllBooks");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("修改book信息有误，"+e.getMessage());
        }

    }

    }
