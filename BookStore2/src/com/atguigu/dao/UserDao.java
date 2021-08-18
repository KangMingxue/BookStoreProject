package com.atguigu.dao;

import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.atguigu.bean.User;

import java.sql.SQLException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:25
 */
public interface UserDao {


    /**
     * 添加user信息
     */
    public void insertUser(User user) throws SQLException;

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    public User selectUserByUserName(String username)throws SQLException;



}

