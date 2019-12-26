package bookstore.goods.comment.domain;

import bookstore.goods.book.domain.Book;
import bookstore.goods.order.domain.Order;

/**
 * ����ʵ���
 * @author 23216
 *
 */
public class Comment {
		private int commentid;//����
		private String bid;//bid
		public void setBid(String bid) {
			this.bid = bid;
		}
		private String comment;//����
		private String recomment;//�ظ�
		private String time;
		private int status;
		
		private Book book;
		private Order order;
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
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public int getCommentid() {
			return commentid;
		}
		public void setCommentid(int commentid) {
			this.commentid = commentid;
		}

		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getRecomment() {
			return recomment;
		}
		public void setRecomment(String recomment) {
			this.recomment = recomment;
		}
		
}
