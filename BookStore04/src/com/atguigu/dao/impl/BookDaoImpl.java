package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/14 15:18
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> selectAllBooks() throws SQLException {
        String sql = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM books";
        return getBeanList(sql);
    }

    @Override
    public void insertBook(Book book)throws SQLException {
        String sql = "INSERT INTO books(title,author,price,sales,stock,img_path) " +
                " VALUES(?,?,?,?,?,?)";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void deleteBook(int bookId) throws SQLException {
        String sql = "DELETE FROM books WHERE id=?";
        update(sql,bookId);
    }

    @Override
    public Book selectBookById(int bookId) throws SQLException {
        String sql = "SELECT id,title,author,price,sales,stock,img_path imgPath FROM books WHERE id=?";
        return getBean(sql,bookId);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title=?,author=?,price=?,sales=?,stock=?,img_path=? WHERE id=?";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }


}
