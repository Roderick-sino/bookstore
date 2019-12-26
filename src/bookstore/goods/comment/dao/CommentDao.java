package bookstore.goods.comment.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import bookstore.goods.book.domain.Book;
import bookstore.goods.comment.domain.Comment;
import bookstore.goods.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class CommentDao {
	private static QueryRunner qr = new TxQueryRunner();
	/**
	 * 加载评论bid
	 * 
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	public static List<Comment> load(String bid) throws SQLException {
		String sql="select * from t_comment where bid=? order by commentid desc";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),bid);
		List<Comment> list = toCommentList(mapList);
		return list;
		
	}
	/**
	 * 搜索
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	public static List<Comment> loadall(String bid) throws SQLException {
		String sql="select * from t_comment ";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Comment> list = toCommentList(mapList);
		return list;
		
	}

	private static List<Comment> toCommentList(List<Map<String, Object>> mapList) {
		// TODO Auto-generated method stub
		List<Comment> commentList = new ArrayList<Comment>();
		for(Map<String, Object> map : mapList) {
			Comment c = CommonUtils.toBean(map, Comment.class);
			commentList.add(c);
		}
		
		
		return commentList;
	}
	
/**
 * 添加
 * @param bid    
 * @param comment  
 * @throws SQLException 
 */
	public void add(String bid, String comment) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 添加当前时间
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String currentTime = dateFormat.format(date1);
		/*
		 * sql语句
		 */
		String sql = "insert into t_comment(bid,comment,time,status)"
				+ " values(?,?,?,0)";
		Object[] params = {bid,comment,currentTime};
//		System.out.println("获取当前时间"+currentTime);
		qr.update(sql, params);
	}
/**
 * 加载
 * @return
 * @throws SQLException
 */
public static List<Comment> findAll() throws SQLException {
	// TODO Auto-generated method stub
	String sql="select * from t_comment order by time";
	List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
	List<Comment> list = toCommentList(mapList);
	return list;
}

/**
 * 删除
 * @param commentid
 * @throws SQLException
 */

public void delete(String commentid) throws SQLException {
	// TODO Auto-generated method stub
	String sql = "delete from t_comment where commentid=?";
	qr.update(sql, commentid);
}
/**
 * 回复
 * @param commentid
 * @param recomment
 * @throws SQLException
 */
public static void editrecomment(String commentid, String recomment) throws SQLException {
	// TODO Auto-generated method stub
	String sql="update t_comment set recomment=? where commentid=?";
	System.out.println(recomment+"    "+commentid);
	Object[] params ={recomment,commentid};
	qr.update(sql, params);
}
public void add1(String bid, String comment) throws SQLException {
	// TODO Auto-generated method stub

	/*
	 * 添加当前时间
	 */
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date1 = new Date();
	String currentTime = dateFormat.format(date1);
	/*
	 * sql语句
	 */
	String sql = "insert into t_comment(bid,comment,time)"
			+ " values(?,?,?)";
	Object[] params = {bid,comment,currentTime};
//	System.out.println("获取当前时间"+currentTime);
	qr.update(sql, params);
}
public static List<Comment> load(String bid, String oid, int status) throws SQLException {
	// TODO Auto-generated method stub
	String sql="select * from t_comment where bid=? and oid=? and status=?";
	List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),bid,oid,status);
	List<Comment> list = toCommentList(mapList);
	return list;
}
public static List<Comment> load1(String oid) throws SQLException {
	// TODO Auto-generated method stub
	String sql="select * from t_comment where oid=? order by commentid desc";
	List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),oid);
	List<Comment> list = toCommentList(mapList);
	return list;
}
public static Comment findbyCid(String commentid) throws SQLException {
	String sql="select * from t_comment where commentid=?";

	
	return qr.query(sql, new BeanHandler<Comment>(Comment.class), commentid);
}
public static Book loadB(String bid) throws SQLException {
	String sql="select bname from t_book  where bid=?";
	return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
}

}
