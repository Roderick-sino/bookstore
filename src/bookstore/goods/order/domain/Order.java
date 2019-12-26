package bookstore.goods.order.domain;

import java.util.List;

import bookstore.goods.book.domain.Book;
import bookstore.goods.comment.domain.Comment;
import bookstore.goods.user.domain.User;

public class Order {

	private String oid;//����
	private String ordertime;//�µ�ʱ��
	private double total;//�ܼ�
	private int status;//����״̬��1wei,2weifahuo,3wei quren,4queren
	private String address;//�ջ���ַ
	private User owner;//������������
	private Comment comment;
	private List<OrderItem> orderItemList;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
