package com.atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

import java.sql.SQLException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 15:29
 */
public class TestUserDao {

    public static void main(String[] args) {

        try {
            UserDao userDao = new UserDaoImpl();

            userDao.insertUser(new User(null,"sadmin","sadmin","sadmin@163.com"));


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
