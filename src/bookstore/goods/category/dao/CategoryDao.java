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
 * 分类持久层
 * 
 * @author 23216
 *
 */

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 把map映射Catgoery
	 */
	private Category toCategory(Map<String, Object> map) {
		/*
		 * cid,cname,pid,orderby
		 */
		Category category = CommonUtils.toBean(map, Category.class);
		String pid = (String) map.get("pid");//额如果一级分类pid-=null
		if (pid != null) {// 如果父分类id不为空，
			/*
			 * 使用一个父分类对象来装在pid 再把父分类设置给category
			 * 
			 */
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		return category;

	}

	/**
	 * 可以吧多个map映射成多了category
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
	 * 返回所有分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findAll() throws SQLException {
		/*
		 * 查询所有一级分类
		 */
		String sql = "select * from t_category where pid is null";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);
		/*
		 * 循环遍历所有的1级分类，为每个一级分类load二级分类
		 */
		for (Category parent : parents) {

			List<Category> children = findByParent(parent.getCid());// 查询
			parent.setChildren(children);// 设置
		}
		return parents;
	}

	/**
	 * 通过父分类查询子分类
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
	 * 添加分类
	 * 
	 * @param category
	 * @throws SQLException
	 */
	public void add(Category category) throws SQLException {
		String sql = "insert into t_category(cid,cname,pid,`desc`) values(?,?,?,?)";
		String pid = null;// 一级分类
		if (category.getParent() != null) {
			pid = category.getParent().getCid();
		}
		Object[] params = { category.getCid(), category.getCname(), pid, category.getDesc() };
		qr.update(sql, params);
	}

	/**
	 * 查询父分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findParents() throws SQLException {
		// 查询所有1级分类
		String sql = "select * from t_category where pid is null order by orderBy";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());

		return toCategoryList(mapList);
	}
	/**
	 * 加载1级\2级分类
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public Category load(String cid) throws SQLException {
		String sql = "select * from t_category where cid=?";
		return toCategory(qr.query(sql, new MapHandler(), cid));
	}
	/**
	 * 修改分类方法
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
	 * 查询指定父类的子类数目
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
	 * 删除分类
	 * @param cid
	 * @throws SQLException
	 */
	public void delete(String cid) throws SQLException {
		String sql = "delete from t_category where cid=?";
		qr.update(sql, cid);
	}
}