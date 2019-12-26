package bookstore.goods.comment.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.goods.book.domain.Book;
import bookstore.goods.comment.dao.CommentDao;
import bookstore.goods.comment.domain.Comment;


public class CommentService {
	private static CommentDao commentDao =new CommentDao();
	/**
	 * 加载
	 * @param bid
	 * @return
	 */
	public  List<Comment> load(String bid) {
		try {
			return CommentDao.load(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加
	 * @param bid
	 */

	public static void add(String bid, String comment) {
		// TODO Auto-generated method stub
		try {
			commentDao.add(bid,comment);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 添加1
	 * @param bid
	 */

	public static void add1(String bid, String comment) {
		// TODO Auto-generated method stub
		try {
			commentDao.add1(bid,comment);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 查找所有评论
	 * @return
	 */
	public Object findAll() {
		// TODO Auto-generated method stub
		try {
			return CommentDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String commentid) {
		// TODO Auto-generated method stub
		try {
			commentDao.delete(commentid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void editrecomment(String commentid, String recomment) {
		// TODO Auto-generated method stub
		try {
			CommentDao.editrecomment(commentid,recomment);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Comment> load(String bid, String oid, int status) {
		// TODO Auto-generated method stub
		try {
			return CommentDao.load(bid,oid,status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Comment> load1(String oid) {
		try {
			return CommentDao.load1(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Object findbyCid(String commentid) {
		// TODO Auto-generated method stub
		try {
			return CommentDao.findbyCid(commentid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Book loadB(String bid) {
		try {
			return CommentDao.loadB(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	
}
