package bookstore.goods.order.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.goods.book.domain.Book;
import bookstore.goods.order.domain.Order;
import bookstore.goods.order.domain.OrderItem;
import bookstore.goods.pager.Expression;
import bookstore.goods.pager.PageBean;
import bookstore.goods.pager.PageConstants;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	
	
	/**
	 * ��ѯ����״̬
	 * @param oid
	 * @return
	 * @throws SQLException 
	 */
	public int findStatus(String oid) throws SQLException {
		String sql = "select status from t_order where oid=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), oid);
		return number.intValue();
	}
	
	/**
	 * �޸Ķ���״̬
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String oid, int status) throws SQLException {
		String sql = "update t_order set status=? where oid=?";
		qr.update(sql, status, oid);
	}
	
	/**
	 * ���ض���(select)
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public Order load(String oid) throws SQLException {
		String sql = "select * from t_order where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		loadOrderItem(order);//Ϊ��ǰ���������������ж�����Ŀ
		return order;
	}
	
	/**
	 * ���ɶ���
	 * @param order
	 * @throws SQLException 
	 */
	public void add(Order order) throws SQLException {
/*insert */
		String sql = "insert into t_order values(?,?,?,?,?,?)";
		Object[] params = {order.getOid(), order.getOrdertime(),
				order.getTotal(),order.getStatus(),order.getAddress(),
				order.getOwner().getUid()};
		
		System.out.println(order.getAddress());
		qr.update(sql, params);
		
		/*
		 * ִ����������ɲ��붩����Ŀ
		 */
		sql = "insert into t_orderitem values(?,?,?,?,?,?,?,?,?)";
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for(int i = 0; i < len; i++){
			OrderItem item = order.getOrderItemList().get(i);
			objs[i] = new Object[]{item.getOrderItemId(),item.getQuantity(),
					item.getSubtotal(),item.getBook().getBid(),
					item.getBook().getBname(),item.getBook().getCurrPrice(),
					item.getBook().getImage_b(),order.getOid(),0};
		}
		qr.batch(sql, objs);
	}
	
	
/**
 * ���û���
 * @param uid
 * @param pc
 * @return
 * @throws SQLException
 */
	public PageBean<Order> findByUser(String uid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("uid", "=", uid));
		return findByCriteria(exprList, pc);
	}
	
	private PageBean<Order> findByCriteria(List<Expression> exprList, int pc) throws SQLException {


		int ps = PageConstants.ORDER_PAGE_SIZE;//ÿҳ��¼��

		StringBuilder whereSql = new StringBuilder(" where 1=1"); 
		List<Object> params = new ArrayList<Object>();//SQL�����ʺţ����Ƕ�Ӧ�ʺŵ�ֵ
		for(Expression expr : exprList) {
		
			whereSql.append(" and ").append(expr.getName())
				.append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and bid = ?
			if(!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

	
		String sql = "select count(*) from t_order" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();//�õ����ܼ�¼��

		sql = "select * from t_order" + whereSql + " order by ordertime desc limit ?,?";
		params.add((pc-1) * ps);//��ǰҳ���м�¼���±�
		params.add(ps);//һ����ѯ���У�����ÿҳ��¼��
		
		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class), 
				params.toArray());
		// ��Ȼ�Ѿ���ȡ���еĶ�������ÿ�������в�û�ж�����Ŀ��
		// ����ÿ��������Ϊ������������ж�����Ŀ
		for(Order order : beanList) {
			loadOrderItem(order);
		}
		
		PageBean<Order> pb = new PageBean<Order>();
		/*
		 * ����PageBeanû��url�����������Servlet���
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	/*
	 * ������������OrderItem
	 */
	private void loadOrderItem(Order order) throws SQLException {

		String sql = "select * from t_orderitem where oid=?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		
		order.setOrderItemList(orderItemList);
	}

	/**
	 * �Ѷ��Mapת���ɶ��OrderItem
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	/*
	 * ��һ��Mapת����һ��OrderItem
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	public PageBean<Order> findByStatus(int status, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("status", "=", status + ""));
		return findByCriteria(exprList, pc);
	}
	public PageBean<Order> findAll(int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}

	public void updateStatus(String oid, String bid, int status) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update t_orderitem set status=? where oid=? and bid=?";
		qr.update(sql, status, oid,bid);
	}
}
