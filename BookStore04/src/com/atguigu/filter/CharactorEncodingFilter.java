package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/18 16:28
 */
public class CharactorEncodingFilter extends HttpFilter {

    /**
     * 处理字符乱码
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");              //解决POST请求乱码
        response.setContentType("text/html;charset=UTF-8"); //解决响应乱码
        //放行请求
        chain.doFilter(request,response);
    }


}
