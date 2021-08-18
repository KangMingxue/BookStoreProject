package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/18 16:19
 */
public abstract class HttpFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 获取FilterConfig对象方法
     * @return
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    /**
     * 获取ServletContext对象方法
     * @throws ServletException
     */
    public ServletContext getServletContext(){
        return getFilterConfig().getServletContext();
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        doFilter(req,res,chain);
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;


    @Override
    public void destroy() {

    }
}
