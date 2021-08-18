package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:44
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 处理注册业务
     (1)处理登录业务Servlet:LoginServlet
     (2)处理注册业务Servlet:RegistServlet
     ①注册成功：重定向regist_success.html
     ②注册失败：提示注册失败【失败信息】
     ③使用BeanUtils工具类，将Map中参数转换为Bean对象中
     (3)注意：处理乱码问题及绝对路径问题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ddd");
        try {
            request.setCharacterEncoding("UTF-8");              //解决POST请求乱码
            response.setContentType("text/html;charset=UTF-8"); //解决响应乱码
            //1. 获取请求参数
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//            String email = request.getParameter("email");
//            User user = new User(null,username,password,email);

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
            response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.html");
        } catch (Exception e) {
            e.printStackTrace();
            //注册失败：提示注册失败【失败信息】
            response.getWriter().write("注册失败："+e.getMessage());
        }
    }
}

