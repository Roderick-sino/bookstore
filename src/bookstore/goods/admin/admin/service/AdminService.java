package bookstore.goods.admin.admin.service;

import java.sql.SQLException;

import bookstore.goods.admin.admin.dao.AdminDao;
import bookstore.goods.admin.admin.domain.Admin;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

	/**
	 * µÇÂ¼
	 * 
	 * @param admin
	 * @return
	 * @throws Exception 
	 */
	public Admin login(Admin admin) throws Exception {
		try {
			return adminDao.find(admin.getAdminname(), admin.getAdminpwd());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
