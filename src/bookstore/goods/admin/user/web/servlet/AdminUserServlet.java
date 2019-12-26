package bookstore.goods.admin.user.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.book.domain.Book;
import bookstore.goods.user.domain.User;
import bookstore.goods.user.service.UserService;
import cn.itcast.servlet.BaseServlet;

public class AdminUserServlet extends BaseServlet {

	/**
	 * 加载用户
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		/*User user = UserService.load();
		req.setAttribute("user", user);*/
		List<User> list = UserService.load();
		req.setAttribute("userList", list);
//		System.out.println("条数："+list.size());
//		for(User user : list){
//			System.out.println("uid："+user.getUid() + "    name:"+user.getLoginname());
//		}
		

		return "f:/adminjsps/admin/users/list.jsp";
	}
/**
 * 删除用户
 * @param req
 * @param resp
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String deleteuser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		UserService.deleteuser(uid);//删除数据库的记录
		
		
		req.setAttribute("msg", "删除用户成功！");
		return "f:/adminjsps/msg.jsp";
	}
}
