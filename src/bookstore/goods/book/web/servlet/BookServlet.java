package bookstore.goods.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.book.domain.Book;
import bookstore.goods.book.service.BookService;
import bookstore.goods.comment.domain.Comment;
import bookstore.goods.comment.service.CommentService;
import bookstore.goods.pager.PageBean;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CommentService commentService = new CommentService();
/**
 * 加载图书
 * @param req
 * @param resp
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		req.setAttribute("book", book);
		
		List<Comment> list = commentService.load(bid);
		req.setAttribute("commentList", list);
		for(Comment comment : list){
		System.out.println("cid："+comment.getComment() + "    rc:"+comment.getRecomment());
	}
		return "f:/jsps/book/desc.jsp";
	}
	
	/**
	 * 获取当前页码
	 * 
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {
				
			}
		}
		return pc;
	}

	/**
	 * 截取url，页面中分页导航需要使用它作为超链接的目标
	 * 
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/ ?method = xxxx
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	/**
	 * 按分类查
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入分类方法");

		
		/*
		 * 得到pc
		 */
		int pc = getPc(req);
		/*
		 * 得到url
		 */
		String url = getUrl(req);

		/*
		 * 获取查询条件,cid即分类id
		 */
		String cid = req.getParameter("cid");
		/*
		 * 使用pc合cid调用findByCategory 得到pagebean
		 */
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		/*
		 * pagebean设置url，转发到list
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";

	}

	/**
	 * 按作者查
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int pc = getPc(req);

		String url = getUrl(req);

		String author = req.getParameter("author");

		PageBean<Book> pb = bookService.findByAuthor(author, pc);

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";

	}

	/**
	 * 按出版社查
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPress(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入按出版社查找方法");
		int pc = getPc(req);
		String url = getUrl(req);
		//String press = req.getParameter("press");
	
		//press =new String(press.getBytes("utf-8"),"ISO-8859-1");
//java.net.URLDecoder.decode(press,"UTF-8");
		String press = url.substring(url.lastIndexOf("=")+1,url.length());
		press = java.net.URLDecoder.decode(press,"UTF-8");
		System.out.println("pc:"+pc + "  url:"+url  + "    press:"+press);
		PageBean<Book> pb = bookService.findByPress(press, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}

	/**
	 * 按书名查
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pc = getPc(req);

		String url = getUrl(req);

		String bname = req.getParameter("bname");

		PageBean<Book> pb = bookService.findByBname(bname, pc);

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";

	}

	/**
	 * 多条件查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		int pc = getPc(req);
	
		String url = getUrl(req);

	
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);

		PageBean<Book> pb = bookService.findByCombination(criteria, pc);

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";

	}
}
