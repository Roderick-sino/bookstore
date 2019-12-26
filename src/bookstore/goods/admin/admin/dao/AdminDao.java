package bookstore.goods.admin.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bookstore.MD5Util;
import bookstore.goods.admin.admin.domain.Admin;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 通过管理员id和mm查询
	 * @param adminname
	 * @param adminpwd
	 * @return
	 * @throws Exception 
	 */
	public Admin find(String adminname, String adminpwd) throws Exception {
		adminpwd=MD5Util.md5Encode(adminpwd);
		String sql = "select * from t_admin where adminname=? and adminpwd=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminname, adminpwd);
	}
}
