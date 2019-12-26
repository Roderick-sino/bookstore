package bookstore.goods.comment.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.book.domain.Book;
import bookstore.goods.comment.domain.Comment;
import bookstore.goods.comment.service.CommentService;
import bookstore.goods.order.domain.Order;
import bookstore.goods.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

public class CommentServlet extends BaseServlet {
	private CommentService commentService = new CommentService();
	private OrderService orderService = new OrderService();

	/**
	 * ��������
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bid = req.getParameter("bid");
		List<Comment> list = commentService.load(bid);
		req.setAttribute("commentList", list);
		for (Comment comment : list) {
			// System.out.println("cid��"+comment.getComment() + "
			// rc:"+comment.getRecomment());
		}
		return "f:/jsps/book/desc.jsp";

	}

	/**
	 * �������
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comment = "���ʣ�"+req.getParameter("comment");

		String bid = req.getParameter("bid");
		CommentService.add(bid, comment);
		// System.out.println("servlet ��"+comment);
		req.setAttribute("msg", "��ӳɹ���");
		return "f:/adminjsps/msg.jsp";
	}
	/**
	 * �������ǰ�ڲ���
	 * @param req
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add1Pre(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		

		
		String oid = req.getParameter("oid");

		String bid = req.getParameter("bid");
		commentService.load(bid);
		
		


		orderService.updateStatus(oid,bid, 1);// ����״̬Ϊ���۳ɹ���
		req.setAttribute("bid", bid);

		Book book=commentService.loadB(bid);
		req.setAttribute("book", book);
		req.setAttribute("oid", oid);
		return "f:/jsps/book/add.jsp";
	}



	/**
	 * ��ӻظ�
	 * @param req
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add1(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String comment =req.getParameter("comment");

		String oid = req.getParameter("oid");
		
		String bid = req.getParameter("bid");
	
		
		CommentService.add1(bid, comment);
		response.setContentType("text/html;charset=UTF-8");//���������ñ���

			req.setAttribute("msg", "������ӳɹ���");

	
			return "f:/jsps/msg.jsp";

	}

}
