package com.atguigu.bean;

import javassist.CtNewConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/17 14:37
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = 3199778867433127059L;
    /**
     * 实现购物车方式
     * 传统互联网项目
     * 购物车中数据，意义不大【无需存储数据库】
         * 购物车中数据存储域对象【pageContext\requset\session\application】
         * 购物车中数据存储会话对象【cookie\session】
         * 总结：购物车中数据，存储session中
     * 大数据项目
         * 购物车中数据，有意义【存储数据库】
         * 以买笔记本电脑为例
            *  50000人的购物车中均有笔记本电脑，但订单表无笔记本电脑数据

     * 业务bean分析
     * CartItem
             book
             count
             amount
     * Cart
             List：有序、可重复   Map：无序、key不可重复的
             购物车中数据有序的，不可重复
             totalCount
             totalAmount
     */

    /**
     * day12

            金额精度问题，解决方案
                1. 设计
                    double->int【1元 = 100分】
                2. 使用BigDecimal





     */


    //购物项集合[key:bookId,value:CartItem]
    private Map<String,CartItem> map = new LinkedHashMap<>();
    //购物车总数量
    private int totalCount;
    //购物车总金额
    private double totalAmount;

    /**
     *  1.添加购物车
     *  2.显示购物车
     *  3.清空购物车
     *  4.删除指定购物项
     *  5.修改购物项数量
     */

    /**
     * 1.添加购物车
     *      思路：判断当前book是否在购物车存在
             存在
                直接将购物项数量+1即可
             不存在
                首先创建购物项
                将book信息添加购物项中
                将购物项数量设置默认值：1
                最终将购物项添加购物车
     */
    public void addBookToCart(Book book){
        //获取购物项
        CartItem cartItem = map.get(book.getId()+"");
        //判断购物项是否为空[判断当前book是否在购物车存在]
        if(cartItem == null){
            //不存在
            //首先创建购物项
            cartItem = new CartItem();
            //将book信息添加购物项中
            cartItem.setBook(book);
            //将购物项数量设置默认值：1
            cartItem.setCount(1);
            // 最终将购物项添加购物车
            map.put(book.getId()+"",cartItem);
        }else{
            //存在【直接将购物项数量+1即可】
            int newCount = cartItem.getCount()+1;
            cartItem.setCount(newCount);
        }

    }

    /**
     * 3.清空购物车
     *      思路：清空map
     */
    public void clearCart(){
        map.clear();
    }


    /**
     * 4.删除指定购物项
     *      思路：删除map中指定购物项
     */
    public void deleteCartItemByBookId(String bookId){
        map.remove(bookId);
    }

    /**
     * 5.修改购物项数量
     */
    public void updateCartItemCount(String bookId,String newCount) throws Exception{
        //确定更改购物项
        CartItem cartItem = map.get(bookId);
        if(cartItem != null){
            int nCount = Integer.parseInt(newCount);
            cartItem.setCount(nCount);
        }
    }


    /**
     *  获取所有购物项集合
     */
    public List<CartItem> getCartItems(){
        return new ArrayList<>(map.values());
    }


    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    /**
     * 计算总数量
     * @return
     */
    public int getTotalCount() {
        int totalCount = 0;
        for (CartItem cartItem : getCartItems()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 计算总金额
     * @return
     */
    public double getTotalAmount() {
        BigDecimal totalAmount = new BigDecimal("0");
//        double totalAmount = 0;
        for (CartItem cartItem : getCartItems()) {
            BigDecimal amount = new BigDecimal(cartItem.getAmount()+"");
//            totalAmount += cartItem.getAmount();
            totalAmount = totalAmount.add(amount);
        }
        return totalAmount.doubleValue();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cart() {
    }

    public Cart(Map<String, CartItem> map, int totalCount, double totalAmount) {
        this.map = map;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
    }
}
