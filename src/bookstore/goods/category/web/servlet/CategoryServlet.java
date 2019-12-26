package bookstore.goods.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.category.domain.Category;
import bookstore.goods.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;
/**
 * ����ģ��web��
 * @author 23216
 *
 */

public class CategoryServlet extends BaseServlet {
		private CategoryService categoryService = new CategoryService();
		/**
		 * ��ѯ���з���
		 * @param req
		 * @param resp
		 * @return 
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String findAll(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {		
			/*
			 * ͨ��service�õ����еķ���
			 * ����ת����left
			 */
			List<Category> parents = categoryService.findAll();
			req.setAttribute("parents", parents);
			
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsps/left.jsp");
//	        try {
//	            dispatcher.forward(req, resp);
//	        } catch (ServletException e) {
//	        } catch (IOException e) {
//	            e.printStackTrace();
	      //  }
	      //  return dispatcher;
	        
			return "/jsps/left.jsp";
		}
		
 }
