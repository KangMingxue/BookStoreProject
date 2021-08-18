package com.atguigu.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 10:39
 */
//@WebServlet(name = "ModelBaseServlet")
public class ModelBaseServlet extends ViewBaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 处理Student请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //区分请求【CRUD】
            String method = request.getParameter("method");
            //通过方法名，获取方法对象，执行方法
            //通过方法名，获取方法对象
            Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            declaredMethod.setAccessible(true);
            //执行方法
            declaredMethod.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
