package com.atguigu.servlet.model;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            User user = userService.doLogin(new User(null, username, password, null));
            //3. 将数据共享域中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
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
           验证码实现步骤
                1. 生成随机数
                2. 将随机数生成图片
                3. 将随机数存储域中
                4. 获取用户输入验证码，与域中随机数比较
                    相等：验证码输入正确
                    不等：验证码输入有误
            使用katpcha实现验证码功能
                1. 导入jar包
                2. 在web.xml注册KatpchaServlet
                3. session域中随机数key:【KAPTCHA_SESSION_KEY】

     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取请求参数
            //获取验证码参数[用户输入验证码]
            String code = request.getParameter("code");
            //获取session域中验证码
            HttpSession session = request.getSession();
            Object sCode = session.getAttribute("KAPTCHA_SESSION_KEY");
            if(sCode != null && sCode.toString().equals(code)){
                //相等：验证码输入正确【移除session域中验证码，保证验证码时效性】
                session.removeAttribute("KAPTCHA_SESSION_KEY");
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
            }else{
                //不等：验证码输入有误
                request.setAttribute("codeMsg","验证码输入有误!");
                //跳转回regist.html页面，提示用户
                processTemplate("user/regist",request,response);
            }
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

    /**
     * 实现注销
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 将user从session域中异常
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //2. 将session失效【不足】
        // 跳转路径
        processTemplate("index",request,response);
    }



}
