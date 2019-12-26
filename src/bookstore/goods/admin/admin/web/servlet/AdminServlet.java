package bookstore.goods.admin.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.admin.admin.domain.Admin;
import bookstore.goods.admin.admin.service.AdminService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();

	/**
	 * ��¼����
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception 
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// ��װ�����ݵ�Admin

		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if (admin == null) {
			req.setAttribute("msg", "�û������������");
			return "/adminjsps/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		return "r:/adminjsps/admin/index.jsp";
	}
	
	/**
	 * ���˳�
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String out(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().removeAttribute("admin");
		return "r:/adminjsps/login.jsp";
		
	}
}
