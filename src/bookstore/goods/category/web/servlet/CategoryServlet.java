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
 * 分类模块web层
 * @author 23216
 *
 */

public class CategoryServlet extends BaseServlet {
		private CategoryService categoryService = new CategoryService();
		/**
		 * 查询所有分类
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
			 * 通过service得到所有的分类
			 * 保存转发到left
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
