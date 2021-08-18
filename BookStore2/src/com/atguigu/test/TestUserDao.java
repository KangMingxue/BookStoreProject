package com.atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

import java.sql.SQLException;

public class TestUserDao {
    public static void main(String[] args) {
        try {
            UserDao userDao = new UserDaoImpl();
            userDao.insertUser(new User(null,"supername","123456","1279@123.com"

            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
