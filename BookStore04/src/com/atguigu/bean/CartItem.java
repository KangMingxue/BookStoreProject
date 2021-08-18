package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/17 14:34
 */
public class CartItem implements Serializable {

    private static final long serialVersionUID = 5550340719163130952L;
    private Book book;      //当前购物项book信息
    private int count;      //当前购物项数量
    private double amount;  //当前购物项金额【计算】


    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        BigDecimal price = new BigDecimal(book.getPrice()+"");
        BigDecimal c = new BigDecimal(count+"");
        double amount = price.multiply(c).doubleValue();
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CartItem() {
    }

    public CartItem(Book book, int count, double amount) {
        this.book = book;
        this.count = count;
        this.amount = amount;
    }
}
