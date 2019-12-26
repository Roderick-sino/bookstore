package bookstore.goods.cart.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.goods.cart.Dao.CartItemDao;
import bookstore.goods.cart.domain.CartItem;
import cn.itcast.commons.CommonUtils;

public class CartItemService {
	
	private CartItemDao cartItemDao=new CartItemDao();
/**
 * ���ض��������Ŀ
 * @param cartItemIds
 * @return
 */
	public List<CartItem> loadCartItems(String cartItemIds) {
		try {
			return cartItemDao.loadCartItems(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * �޸�����
	 * @param cartItemId
	 * @param quantity
	 * @return
	 */
	public CartItem updateQuantity(String cartItemId,int quantity){
		try {
			cartItemDao.updateQuantity(cartItemId, quantity);
			return cartItemDao.findByCartItemId(cartItemId);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ����ɾ��
	 * @param cartItemIds
	 */
	public void batchDelete(String cartItemIds) {
		try {
			cartItemDao.batchDelete(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �����Ŀ
	 * @param cartItem
	 */
	
	public void add(CartItem cartItem) {
		try {
			CartItem _cartItem = cartItemDao.findByUidAndBid(
					cartItem.getUser().getUid(),
					cartItem.getBook().getBid());
			if(_cartItem == null) {//���ԭ��û�������Ŀ����ô�����Ŀ
				cartItem.setCartItemId(CommonUtils.uuid());
				cartItemDao.addCartItem(cartItem);
			} else {//���ԭ���������Ŀ���޸�����
				// ʹ��ԭ������������Ŀ����֮��������Ϊ�µ�����
				int quantity = cartItem.getQuantity() + _cartItem.getQuantity();
				// �޸��������Ŀ������
				cartItemDao.updateQuantity(_cartItem.getCartItemId(), quantity);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * �ҵĹ��ﳵ����
	 * @param uid
	 * @return
	 */
	public List<CartItem> myCart(String uid) {
		try {
			return cartItemDao.findByUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	 }
}
