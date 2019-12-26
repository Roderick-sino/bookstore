package bookstore.goods.category.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.goods.category.dao.CategoryDao;
import bookstore.goods.category.domain.Category;

/**
 * ����ҵ���
 * @author 23216
 *
 */
public class CategoryService {
	private CategoryDao categoryDao=new CategoryDao();
	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Category> findAll() {
		try {
			return categoryDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��ӷ���
	 * @param category
	 */
	
	public void add(Category category) {
		try {
			categoryDao.add(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����1������
	 * @return
	 */
	public List<Category> findParents() {
		try {
			return categoryDao.findParents();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���ط���
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		try {
			return categoryDao.load(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * �޸ķ���
	 * @param category
	 */
	public void edit(Category category) {
		try {
			categoryDao.edit(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ɾ������
	 * @param cid
	 */
	public void delete(String cid) {
		try {
			categoryDao.delete(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��ѯָ�������µ���Ŀ
	 * @param pid
	 * @return
	 */
	public int findChildrenCountByParent(String pid) {
		try {
			return categoryDao.findChildrenCountByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���ҷ�����µ��ӷ���
	 * @param pid
	 * @return
	 */
	public List<Category> findChildren(String pid) {
		try {
			return categoryDao.findByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
