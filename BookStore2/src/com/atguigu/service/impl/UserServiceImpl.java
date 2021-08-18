package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;
import com.atguigu.utils.MD5Util;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:33
 */
public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();


    /**
     * 注册业务：
     * 判断用户名是否存在
     * 存在：抛出异常，提示用户注册失败【用户名已存在！】
     * 不存在：调用insertUser()添加用户信息
     * 注册时，密码需要使用加密器进行加密后，再添加到数据库。
     * @param user
     * @throws Exception
     */
    @Override
    public void doRegist(User user) throws Exception {
        //判断用户名是否存在
        User userRs = userDao.selectUserByUserName(user.getUsername());
        if(userRs == null){
            //不存在：调用insertUser()添加用户信息
            //注册时，密码需要使用加密器进行加密后，再添加到数据库。
            //使用MD5加密器，将密码加密
            String mdPwd = MD5Util.encode(user.getPassword());
            //将加密后密码设置回User对象中
            user.setPassword(mdPwd);
            userDao.insertUser(user);
        }else{
            //存在：抛出异常，提示用户注册失败【用户名已存在！】
            throw new RuntimeException("用户名已存在！");
        }
    }

    /**
     登录业务
     判断用户名是否存在
     存在：获取数据库[user]中密码，与用户输入密码【加密后】比较。
     密码相等：登录成功，重定向login_success.html
     密码不等：抛出异常，提示用户登录失败【密码有误，请重新输入！】
     不存在：抛出异常，提示用户登录失败【用户名不存在】
     * @param user
     * @throws Exception
     */
    @Override
    public void doLogin(User user) throws Exception {
        //判断用户名是否存在
        User userRs = userDao.selectUserByUserName(user.getUsername());
        if(userRs == null){
            //不存在：抛出异常，提示用户登录失败【用户名不存在】
            throw new RuntimeException("用户名不存在！");
        }else{
            //存在：获取数据库[user]中密码，与用户输入密码【加密后】比较。
            //数据库中密码
            String password = userRs.getPassword();
            //获取用户输入密码
            String uPwd = user.getPassword();
            if(!password.equals(MD5Util.encode(uPwd))){
                //密码不等：抛出异常，提示用户登录失败【密码有误，请重新输入！】
                throw new RuntimeException("密码有误，请重新输入！");
            }
//            else{
//               //密码相等：登录成功，重定向login_success.html
//            }
        }

    }

}

