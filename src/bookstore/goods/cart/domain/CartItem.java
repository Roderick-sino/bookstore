package bookstore.goods.cart.domain;

import java.math.BigDecimal;

import bookstore.goods.book.domain.Book;
import bookstore.goods.user.domain.User;

public class CartItem {
	private String cartItemId;// 主键
	private int quantity;// 数量
	private Book book;// 条目对应的图书
	private User user;// 所属用户
	private String  bid;
	private String uid;
	private int status;
	
	
public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
/**
 * 小计
 * @return
 */
	public double getSubtotal(){
		/*
		 * 无误差
		 * 二进制对于10分之1会有无限循环2.0-1.1=0.8999999999999999999
		 */
		BigDecimal b1= new BigDecimal(book.getCurrPrice()+"");
		BigDecimal b2= new BigDecimal(quantity+"");
		BigDecimal b3=b1.multiply(b2);
		return b3.doubleValue();
		
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
