package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 16:27
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 处理登录业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");              //解决POST请求乱码
        response.setContentType("text/html;charset=UTF-8"); //解决响应乱码
        try {
            //1. 获取请求参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //2. 调用service中相应方法
            userService.doLogin(new User(null,username,password,null));
            //3.  ....
            //4.路径跳转【登录成功，重定向login_success.html】
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
        } catch (Exception e) {
            e.printStackTrace();
            //响应流，响应数据
            response.getWriter().write("登录失败："+e.getMessage());
        }

    }

}

