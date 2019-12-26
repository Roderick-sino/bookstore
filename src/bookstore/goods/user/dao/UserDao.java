package bookstore.goods.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.MD5Util;
import bookstore.goods.category.domain.Category;
import bookstore.goods.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * �û�ģ��־ò�
 * 
 * @author 23216
 *
 */
public class UserDao {
	private static QueryRunner qr = new TxQueryRunner();

	/**
	 * ��uid��password��ѯ
	 * 
	 * @param uid
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public boolean findByUidAndPassword(String uid, String password) throws Exception {
		password=MD5Util.md5Encode(password);
		String sql = "select count(*) from t_user where uid=? and loginpass=?";
		
		Number number = (Number) qr.query(sql, new ScalarHandler(), uid, password);
		return number.intValue() > 0;
	}

	/**
	 * �޸�����
	 * 
	 * @param uid
	 * @param password
	 * @throws Exception 
	 */
	public void updatePassword(String uid, String password) throws Exception {
		password=MD5Util.md5Encode(password);
		String sql = "update t_user set loginpass=? where uid=?";
		qr.update(sql, password, uid);
	}

	/**
	 * ���û����������ѯ
	 * 
	 * @param loginname
	 * @param loginpass
	 * @return
	 * @throws Exception 
	 */
	public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws Exception {
		loginpass=MD5Util.md5Encode(loginpass);
		String sql = "select * from t_user where loginname=? and loginpass=?";
		return qr.query(sql, new BeanHandler<User>(User.class), loginname, loginpass);
	}

	/**
	 * ͨ���������ѯ�û�
	 * 
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public User findByCode(String code) throws SQLException {
		String sql = "select * from t_user where activationCode=?";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}

	/**
	 * �޸��û�״̬
	 * 
	 * @param uid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String uid, boolean status) throws SQLException {
		String sql = "update t_user set status=? where uid=?";
		qr.update(sql, status, uid);
	}

	/**
	 * У���û����Ƿ�ע��
	 * 
	 * @param loginname
	 * @return
	 * @throws SQLException
	 */
	public boolean ajaxValidateLoginname(String loginname) throws SQLException {
		String sql = "select count(1) from t_user where loginname=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(), loginname);
		return number.intValue() == 0;
	}

	/**
	 * У��Email�Ƿ�ע��
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean ajaxValidateEmail(String email) throws SQLException {
		String sql = "select count(1) from t_user where email=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(), email);
		return number.intValue() == 0;
	}

	/**
	 * ����û�
	 * 
	 * @param user
	 * @throws Exception 
	 */
	public void add(User user) throws Exception {
	
		String sql = "insert into t_user values(?,?,?,?,?,?)";
		String pwd=MD5Util.md5Encode(user.getLoginpass());
		Object[] params = { user.getUid(), user.getLoginname(), pwd, user.getEmail(), user.isStatus(),
				user.getActivationCode() };
		qr.update(sql, params);
	}

	/**
	 * �����û�
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
/*	public static  User load() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from t_user ";
		
		return qr.query(sql, new BeanHandler<User>(User.class),2);
	}*/
	
	
	public static List<User> load() throws SQLException {
		/*
		 * ��ѯ����һ������
		 */
		String sql = "select * from t_user";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<User> list = toUserList(mapList);
		return list;
	}
	
	private static List<User> toUserList(List<Map<String, Object>> mapList) {
		List<User> userList = new ArrayList<User>();
		for (Map<String, Object> map : mapList) {
			User c = CommonUtils.toBean(map, User.class);
			userList.add(c);
		}
		return userList;
	}
/**
 * ɾ���û�
 * @param uid
 * @throws SQLException 
 */


public static void deleteuser(String uid) throws SQLException {
	// TODO Auto-generated method stub
	String sql = "delete from t_user where uid=?";
	qr.update(sql, uid);
}
	
	
}
