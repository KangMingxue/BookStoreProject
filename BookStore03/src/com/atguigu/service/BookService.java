package com.atguigu.service;

import com.atguigu.bean.Book;

import java.util.List;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 15:19
 */
public interface BookService {


    /**
     * 查询所有book信息
     */
    public List<Book>  getAllBooks() throws Exception;

    /**
     * 添加book信息
     */
    public void saveBook(Book book) throws Exception;

    /**
     * 删除book信息
     * @param bookId
     * @throws Exception
     */
    public void deleteBookById(String bookId) throws Exception;

}
