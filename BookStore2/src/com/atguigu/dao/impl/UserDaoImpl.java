package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

import java.sql.SQLException;


/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:27
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username,`password`,email) VALUES(?,?,?)";
        update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
//每个接口写完判断一下
    @Override
    public User selectUserByUserName(String username) throws SQLException {
        String sql = "SELECT id,username,`password`,email FROM users WHERE username=?";
        return getBean(sql,username);
    }

}