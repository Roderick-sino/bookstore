package bookstore.goods.admin.admin.domain;

import bookstore.MD5Util;
import bookstore.goods.admin.admin.dao.AdminDao;

public class Admin {
	private String adminId;//Ö÷¼ü
	private String adminname;//µÇÂ¼Ãû
	private String adminpwd;//ÃÜÂë
	private MD5Util md5 = new MD5Util();
	

	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		
		this.adminpwd = adminpwd;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminname=" + adminname + ", adminpwd=" + adminpwd + "]";
	}
	
}
