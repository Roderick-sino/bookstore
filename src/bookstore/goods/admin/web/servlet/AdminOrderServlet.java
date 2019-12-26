package bookstore.goods.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.order.domain.Order;
import bookstore.goods.order.service.OrderService;
import bookstore.goods.pager.PageBean;
import cn.itcast.servlet.BaseServlet;

public class AdminOrderServlet extends BaseServlet {

private OrderService orderService = new OrderService();
	
	/**
	 * ��ȡ��ǰҳ��
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	
	/**
	 * ��ȡurl��ҳ���еķ�ҳ��������Ҫʹ������Ϊ�����ӵ�Ŀ�꣡
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * ���url�д���pc��������ȡ��������������ǾͲ��ý�ȡ��
		 */
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	/**
	 * �鿴���ж���
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 *  �õ�pc�����ҳ�洫�ݣ�ʹ��ҳ��ģ����û����pc=1
		 */
		int pc = getPc(req);
		/*
		 * �õ�url��...
		 */
		String url = getUrl(req);
		
		/*
		 *  ʹ��pc��cid����service#findByCategory�õ�PageBean
		 */
		PageBean<Order> pb = orderService.findAll(pc);
		/*
		 * ��PageBean����url������PageBean��ת����/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	/**
	 * ��״̬��ѯ
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		int status = Integer.parseInt(req.getParameter("status"));
		PageBean<Order> pb = orderService.findByStatus(status, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	/**
	 * �鿴������ϸ��Ϣ
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");//btn˵�����û�����ĸ������������ʱ�������
		req.setAttribute("btn", btn);
		return "/adminjsps/admin/order/desc.jsp";
	}
	
	/**
	 * ȡ������
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * У�鶩��״̬
		 */
		int status = orderService.findStatus(oid);
		if(status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "״̬���ԣ�����ȡ����");
			return "f:/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);//����״̬Ϊȡ����
		req.setAttribute("code", "success");
		req.setAttribute("msg", "���Ķ�����ȡ����");
		return "f:/adminjsps/msg.jsp";		
	}
	
	/**
	 * ��������
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deliver(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * У�鶩��״̬
		 */
		int status = orderService.findStatus(oid);
		if(status != 2) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "״̬���ԣ����ܷ�����");
			return "f:/adminjsps/msg.jsp";
		}
		orderService.updateStatus(oid, 3);//����״̬Ϊȡ����
		req.setAttribute("code", "success");
		req.setAttribute("msg", "���Ķ����ѷ�������鿴����������ȷ�ϰɣ�");
		return "f:/adminjsps/msg.jsp";		
	}
}
