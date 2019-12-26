package bookstore.goods.book.service;

import java.sql.SQLException;

import bookstore.goods.book.dao.BookDao;
import bookstore.goods.book.domain.Book;
import bookstore.goods.pager.PageBean;

public class BookService {
	private BookDao bookDao =new BookDao();
	
	
	/**
	 * ����ͼ������
	 * @param cid
	 * @return
	 */
	public int findBookCountByCategory(String cid) {
		try {
			return bookDao.findBookCountByCategory(cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����book 
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		try {
			return bookDao.findByBid(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ������� 
	 * @param cid
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByCategory(String cid, int pc) {
		try {
			return bookDao.findByCategory(cid, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ������%��
	 * @param bname
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByBname(String bname, int pc) {
		try {
			return bookDao.findByBname(bname, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �����߲�
	 * @param author
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByAuthor(String author, int pc) {
		try {
			return bookDao.findByAuthor(author, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���������
	 * @param press
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByPress(String press, int pc) {
		try {
			return bookDao.findByPress(press, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��������ϲ�ѯ
	 * @param criteria
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByCombination(Book criteria, int pc) {
		try {
			return bookDao.findByCombination(criteria, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���ͼ��
	 * @param book
	 */
	public void add(Book book) {
		try {
			bookDao.add(book);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ɾ��ͼ��
	 * @param bid
	 */
	public void delete(String bid) {
		try {
			bookDao.delete(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �޸�ͼ��
	 * @param book
	 */
	public void edit(Book book) {
		try {
			bookDao.edit(book);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}