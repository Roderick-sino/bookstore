 package bookstore.goods.admin.comment.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.comment.domain.Comment;
import bookstore.goods.comment.service.CommentService;
import cn.itcast.servlet.BaseServlet;

public class AdminCommentServlet extends BaseServlet {
	private CommentService commentService = new CommentService();
	/**
 * ������������
 * @param req
 * @param resp
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("comment", commentService.findAll());
		return "f:/adminjsps/admin/comment/list.jsp";
	}

	/**
	 * ɾ��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String commentid= req.getParameter("commentid");
		commentService.delete(commentid);
   //     System.out.println("ɾ��"+commentid);
		return findAll(req, resp);

	}
	/**
	 * �޸Ļظ���һ��
	 */
	public String editrecommentPre(HttpServletRequest req, HttpServletResponse resp){
		String commentid= req.getParameter("commentid");

		
		req.setAttribute("comment", commentService.findbyCid(commentid));
	System.out.print(commentService.findbyCid(commentid));
		return "f:/adminjsps/admin/comment/add.jsp";
	}
/**
 * �޸Ļظ�
 * @param req
 * @param resp
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String editrecomment(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String commentid= req.getParameter("commentid");
		String recomment= req.getParameter("recomment");
		
		System.out.println(commentid+"   "+recomment);
		commentService.editrecomment(commentid,recomment);
		return findAll(req, resp);
	
	}
}