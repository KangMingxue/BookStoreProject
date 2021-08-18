package com.atguigu.servlet.model;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 11:34
 */
public class UserServlet extends ModelBaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 跳转登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/login",request,response);
    }

    /**
     * 跳转注册页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegistPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/regist",request,response);
    }

    /**
     * 处理登录请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //2. 调用service中相应方法
            userService.doLogin(new User(null,username,password,null));
            //3.  ....
            //4.路径跳转【登录成功，重定向login_success.html】
//            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
            processTemplate("user/login_success",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //将登录失败信息，共享request域
            request.setAttribute("msg","登录失败："+e.getMessage());
            //响应流，响应数据
//            response.getWriter().write("登录失败："+e.getMessage());
            processTemplate("user/login",request,response);
        }
    }

    /**
     * 处理注册请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            //一次性获取所有参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            //将map中参数注入到user中
            User user = new User();
            //map中key与User对象中的属性一致
            BeanUtils.populate(user,parameterMap);
            //2. 调用service中的相应方法
            userService.doRegist(user);
            //3. 将数据存放到域中共享数据
            //4. 路径跳转[注册成功：重定向regist_success.html]
//            response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.html");
            processTemplate("user/regist_success",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //注册失败：提示注册失败【失败信息】
//            response.getWriter().write("注册失败："+e.getMessage());
            //将错误信息共享request域中
            request.setAttribute("msg","注册失败："+e.getMessage());
            //跳转回regist.html页面，提示用户
            processTemplate("user/regist",request,response);
        }
    }

}
