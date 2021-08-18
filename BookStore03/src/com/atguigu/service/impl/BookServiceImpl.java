package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 15:20
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAllBooks() throws Exception {
        return bookDao.selectAllBooks();
    }

    @Override
    public void saveBook(Book book) throws Exception {
        bookDao.insertBook(book);
    }

    @Override
    public void deleteBookById(String bookId) throws Exception {
        int bId = Integer.parseInt(bookId);
        bookDao.deleteBook(bId);
    }


}
