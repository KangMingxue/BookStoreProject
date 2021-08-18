package com.atguigu.servlet.model;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/17 15:30
 */
public class CartServlet extends ModelBaseServlet {

//    private Cart cart = new Cart();     //错误的，Servlet是单例，Cart全局只有一个。

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加book到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            String bookId = request.getParameter("bookId");
            //2. 调用Cart
            // Cart cart = new Cart();  //错误的，每次调用方法，初始化Cart
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("cart");
            //第一次从session中获取数据，cart==null
            if(cart == null){
                cart = new Cart();
                //将购物车存放session中
                session.setAttribute("cart",cart);
            }
            //通过bookId获取book
            Book book = bookService.getBookByBookId(bookId);
            //调用cart中相应方法，实现添加购物车功能
            cart.addBookToCart(book);
            //3. 将数据共享域
            //4. 路径跳转【跳转回首页】
            response.sendRedirect(request.getContextPath()+"/index.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("添加购物车失败！");
        }

    }

    /**
     * 跳转购物车页面【显示购物车数据】
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("cart/cart",request,response);
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cart
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart != null){
            cart.clearCart();
        }
        //跳转回cart.html
        processTemplate("cart/cart",request,response);
    }

    /**
     * 删除指定购物项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数bookId
        String bookId = request.getParameter("bookId");
        //获取Cart
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart != null){
            cart.deleteCartItemByBookId(bookId);
        }
        processTemplate("cart/cart",request,response);
    }

    /**
     * 更改购物项数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void upateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取参数
        try {
            String bookId = request.getParameter("bookId");
            String newCount = request.getParameter("newCount");
            //2. 调用cart
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("cart");
            if(cart != null){
                cart.updateCartItemCount(bookId,newCount);
            }
            //跳转回cart.html
            processTemplate("cart/cart",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("更改购物项数量有误！");
        }
    }


}
