package bookstore.goods.category.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.goods.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * ����־ò�
 * 
 * @author 23216
 *
 */

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * ��mapӳ��Catgoery
	 */
	private Category toCategory(Map<String, Object> map) {
		/*
		 * cid,cname,pid,orderby
		 */
		Category category = CommonUtils.toBean(map, Category.class);
		String pid = (String) map.get("pid");//�����һ������pid-=null
		if (pid != null) {// ���������id��Ϊ�գ�
			/*
			 * ʹ��һ�������������װ��pid �ٰѸ��������ø�category
			 * 
			 */
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		return category;

	}

	/**
	 * ���԰ɶ��mapӳ��ɶ���category
	 * 
	 * @param mapList
	 * @return
	 */
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {

		List<Category> categoryList = new ArrayList<Category>();
		for (Map<String, Object> map : mapList) {
			Category c = toCategory(map);
			categoryList.add(c);
		}
		return categoryList;

	}

	/**
	 * �������з���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findAll() throws SQLException {
		/*
		 * ��ѯ����һ������
		 */
		String sql = "select * from t_category where pid is null";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);
		/*
		 * ѭ���������е�1�����࣬Ϊÿ��һ������load��������
		 */
		for (Category parent : parents) {

			List<Category> children = findByParent(parent.getCid());// ��ѯ
			parent.setChildren(children);// ����
		}
		return parents;
	}

	/**
	 * ͨ���������ѯ�ӷ���
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findByParent(String pid) throws SQLException {
		String sql = "select * from t_category where pid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), pid);
		return toCategoryList(mapList);

	}

	/**
	 * ��ӷ���
	 * 
	 * @param category
	 * @throws SQLException
	 */
	public void add(Category category) throws SQLException {
		String sql = "insert into t_category(cid,cname,pid,`desc`) values(?,?,?,?)";
		String pid = null;// һ������
		if (category.getParent() != null) {
			pid = category.getParent().getCid();
		}
		Object[] params = { category.getCid(), category.getCname(), pid, category.getDesc() };
		qr.update(sql, params);
	}

	/**
	 * ��ѯ������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findParents() throws SQLException {
		// ��ѯ����1������
		String sql = "select * from t_category where pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		return toCategoryList(mapList);
	}
	/**
	 * ����1��\2������
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public Category load(String cid) throws SQLException {
		String sql = "select * from t_category where cid=?";
		return toCategory(qr.query(sql, new MapHandler(), cid));
	}
	/**
	 * �޸ķ��෽��
	 * @param category
	 * @throws SQLException
	 */
	public void edit(Category category) throws SQLException {
		String sql = "update t_category set cname=?, pid=?, `desc`=? where cid=?";
		String pid = null;
		if(category.getParent() != null) {
			pid = category.getParent().getCid();
		}
		Object[] params = {category.getCname(), pid, category.getDesc(), category.getCid()};
		qr.update(sql, params);
	}
	/**
	 * ��ѯָ�������������Ŀ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public int findChildrenCountByParent(String pid) throws SQLException {
		String sql = "select count(*) from t_category where pid=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), pid);
		return cnt == null ? 0 : cnt.intValue();
	}
	/**
	 * ɾ������
	 * @param cid
	 * @throws SQLException
	 */
	public void delete(String cid) throws SQLException {
		String sql = "delete from t_category where cid=?";
		qr.update(sql, cid);
	}
}