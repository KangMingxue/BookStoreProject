package com.atguigu.dao;

import com.atguigu.bean.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 15:17
 */
public interface BookDao {


    /**
     * 查询所有book信息
     */
    public List<Book> selectAllBooks() throws SQLException;


    /**
     * 添加book信息
     */
    public void insertBook(Book book) throws SQLException;

    /**
     * 删除book信息
     */
    public void deleteBook(int bookId) throws SQLException;

    /**
     * 通过bookId，查询book信息
     */
    public Book selectBookById(int bookId) throws  SQLException;

    /**
     * 修改book信息
     */
    public void updateBook(Book book) throws SQLException;

}
