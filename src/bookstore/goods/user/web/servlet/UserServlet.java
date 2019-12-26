package bookstore.goods.user.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookstore.goods.user.domain.User;
import bookstore.goods.user.service.UserService;
import bookstore.goods.user.service.exception.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * �û�ģ��WEB��
 * 
 * @author 23216
 *
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();

	/**
	 * ajax�û����Ƿ�ע��У��
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * ��ȡ�û���
		 */
		String loginname = req.getParameter("loginname");
		/*
		 *. ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateLoginname(loginname);
		/*
		 * �����ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ajax Email�Ƿ�ע��У��
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * ��ȡEmail
		 */
		String email = req.getParameter("email");
		/*
		 * ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * �����ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ajax��֤���Ƿ���ȷУ��
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * ��ȡ������е���֤��
		 */
		String verifyCode = req.getParameter("verifyCode");
		/*
		 * ��ȡͼƬ����ʵ��У����
		 */
		String vcode = (String) req.getSession().getAttribute("vCode");
		/*
		 *  ���к��Դ�Сд�Ƚϣ��õ����
		 */
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		/*
		 * ���͸��ͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * ע�Ṧ��
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception 
	 */
	public String regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/*
		 * ��װ�����ݵ�User����
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * У��֮, ���У��ʧ�ܣ����������Ϣ�����ص�regist.jsp��ʾ
		 */
		Map<String, String> errors = validateRegist(formUser, req.getSession());
		if (errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		/*
		 * ʹ��service���ҵ��
		 */
		userService.regist(formUser);
		/*
		 * ����ɹ���Ϣ��ת����msg.jsp��ʾ��
		 */
		req.setAttribute("code", "success");
		req.setAttribute("msg", "ע�Ṧ�ܣ������ϵ����伤�");
		return "f:/jsps/msg.jsp";
	}

	/*
	 * ע��У�� �Ա����ֶν������У�飬����д���ʹ�õ�ǰ�ֶ�����Ϊkey��������ϢΪvalue�����浽map�� ����map
	 */
	private Map<String, String> validateRegist(User formUser, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		/*
		 * У���¼��
		 */
		String loginname = formUser.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "�û�������Ϊ�գ�");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "�û������ȱ�����3~20֮�䣡");
		} else if (!userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "�û����ѱ�ע�ᣡ");
		}

		/*
		 * У���¼����
		 */
		String loginpass = formUser.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "���벻��Ϊ�գ�");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "���볤�ȱ�����3~20֮�䣡");
		}

		/*
		 * ȷ������У��
		 */
		String reloginpass = formUser.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "ȷ�����벻��Ϊ�գ�");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "�������벻һ�£�");
		}

		/*
		 * У��email
		 */
		String email = formUser.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email����Ϊ�գ�");
		} else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email��ʽ����");
		} else if (!userService.ajaxValidateEmail(email)) {
			errors.put("email", "Email�ѱ�ע�ᣡ");
		}

		/*
		 * ��֤��У��
		 */
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "��֤�����");
		}

		return errors;
	}

	/**
	 * �����
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String activation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * ��ȡ���������� �ü��������service������ɼ��� ����ɹ���Ϣ��request��ת����msg.jsp��ʾ��
		 */
		String code = req.getParameter("activationCode");
		try {
			userService.activatioin(code);
			req.setAttribute("code", "success");// ֪ͨmsg.jsp��ʾ�Ժ�
			req.setAttribute("msg", "��ϲ������ɹ��������ϵ�¼��");
		} catch (UserException e) {
			// ˵��service�׳����쳣
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("code", "error");// ֪ͨmsg.jsp��ʾX
		}
		return "f:/jsps/msg.jsp";
	}

	/**
	 * �޸�����
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception 
	 */
	public String updatePassword(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		/*
		 * ��װ�����ݵ�user�� ��session�л�ȡuid ʹ��uid�ͱ��е�oldPass��newPass������service����
		 * ����ɹ���Ϣ��rquest�� ת����msg.jsp
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		User user = (User) req.getSession().getAttribute("sessionUser");
		// ����û�û�е�¼�����ص���¼ҳ�棬��ʾ������Ϣ
		if (user == null) {
			req.setAttribute("msg", "����û�е�¼��");
			return "f:/jsps/user/login.jsp";
		}

		try {
			userService.updatePassword(user.getUid(), formUser.getNewpass(), formUser.getLoginpass());
			req.setAttribute("msg", "�޸�����ɹ�");
			req.setAttribute("code", "success");
			return "f:/jsps/msg.jsp";
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());// �����쳣��Ϣ��request
			req.setAttribute("user", formUser);// Ϊ�˻���
			return "f:/jsps/user/pwd.jsp";
		}
	}

	/**
	 * �˳�����
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}

	/**
	 * ��¼����
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception 
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		/*
		 * ��װ�����ݵ�user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * У��
		 */
		Map<String, String> errors = validateLogin(formUser, req.getSession());
		if (errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}

		/*
		 * ����userService#login()����
		 */
		User user = userService.login(formUser);
		/*
		 * ��ʼ�ж�
		 */
		if (user == null) {
			req.setAttribute("msg", "�û������������");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		} else {
			if (!user.isStatus()) {
				req.setAttribute("msg", "����û�м��");
				req.setAttribute("user", formUser);
				return "f:/jsps/user/login.jsp";
			} else {
				// �����û���session
				req.getSession().setAttribute("sessionUser", user);
				// ��ȡ�û������浽cookie��
				String loginname = user.getLoginname();
				loginname = URLEncoder.encode(loginname, "utf-8");// cockie���������ı���
				Cookie cookie = new Cookie("loginname", loginname);
				cookie.setMaxAge( 60 * 60 * 24 * 10);// ����10�죬cookie����
				resp.addCookie(cookie);
				return "r:/index.jsp";// �ض�����ҳ
			}
		}
	}


	/*
	 * ��¼У�鷽������ע�����ƣ�
	 */
	private Map<String, String> validateLogin(User formUser, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		// У���¼��
		 
		String loginname = formUser.getLoginname();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "�û�������Ϊ�գ�");
		} else if(loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "�û������ȱ�����3~20֮�䣡");
		}else if(userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "�û��������ڣ�");
		}
		/*
		 *  ��֤��У��
		 */
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		} else if(!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "��֤�����");
		}
			return errors;
		}
	/**
	 * �˳�
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String out(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().removeAttribute("sessionUser");
		return "r:/jsps/user/login.jsp";
		
	}
	
	}

