package com.atguigu.service;

import com.atguigu.bean.User;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:33
 */
public interface UserService {


    /**
     * 注册业务
     * @param user
     * @throws Exception
     */
    public void doRegist(User user) throws Exception;


    /**
     * 登录业务
     */
    public void doLogin(User user) throws Exception;

}
