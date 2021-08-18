package com.atguigu.test;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;

import java.sql.SQLException;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 16:16
 */
public class TestBookDao {


    public static void main(String[] args) {

        try {
            BookDao bookDao = new BookDaoImpl();

            // public Book(Integer id, String title, String author, double price, Integer sales, Integer stock, String imgPath) {
            bookDao.insertBook(new Book(null,"testTitel","testAuthor",15,100,100,null));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
