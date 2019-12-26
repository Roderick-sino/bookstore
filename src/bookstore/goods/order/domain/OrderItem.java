package bookstore.goods.order.domain;

import bookstore.goods.book.domain.Book;

public class OrderItem {
	private String orderItemId;//����
	private int quantity;//����
	private double subtotal;//С��
	private Book book;//��������Book
	private Order order;//�����Ķ���
	private int status;
	
	
	public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
