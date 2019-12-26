package bookstore.goods.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import bookstore.goods.user.dao.UserDao;
import bookstore.goods.user.domain.User;
import bookstore.goods.user.service.exception.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
 * �û�ģ��ҵ���
 * 
 * @author 23216
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * �޸�����
	 * 
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws Exception
	 */
	public void updatePassword(String uid, String newPass, String oldPass) throws Exception {

		try {
			/*
			 * У��������
			 */
			boolean bool = userDao.findByUidAndPassword(uid, oldPass);
			if (!bool) {// ������������
				throw new UserException("���������");
			}

			/*
			 * �޸�����
			 */
			userDao.updatePassword(uid, newPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��¼����
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user) throws Exception {
		try {
			return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �����
	 * 
	 * @param code
	 * @throws UserException
	 */
	public void activatioin(String code) throws UserException {
		/*
		 * ͨ���������ѯ�û� ���UserΪnull��˵������Ч�����룬�׳��쳣�������쳣��Ϣ����Ч�����룩
		 * �鿴�û�״̬�Ƿ�Ϊtrue�����Ϊtrue���׳��쳣�������쳣��Ϣ �޸��û�״̬Ϊtrue
		 */
		try {
			User user = userDao.findByCode(code);
			if (user == null)
				throw new UserException("��Ч�ļ����룡");
			if (user.isStatus())
				throw new UserException("���Ѿ�������ˣ���Ҫ���μ��");
			userDao.updateStatus(user.getUid(), true);// �޸�״̬
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �û���ע��У��
	 * 
	 * @param loginname
	 * @return
	 */
	public boolean ajaxValidateLoginname(String loginname) {
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * EmailУ��
	 * 
	 * @param email
	 * @return
	 */
	public boolean ajaxValidateEmail(String email) {
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ע�Ṧ��
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception {
		/*
		 * ���ݵĲ���
		 */
		user.setUid(CommonUtils.uuid());
		user.setStatus(false);
		user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
		/*
		 * �����ݿ����
		 */
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/*
		 * ���ʼ�
		 */
		/*
		 * �������ļ����ݼ��ص�prop��
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * ��¼�ʼ����������õ�session
		 */
		String host = prop.getProperty("host");// ������������
		String name = prop.getProperty("username");// ��¼��
		String pass = prop.getProperty("password");// ��¼����
		Session session = MailUtils.createSession(host, name, pass);

		/*
		 * ����Mail����
		 */
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		// MessageForm.format������ѵ�һ�������е�{0},ʹ�õڶ����������滻��
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		/*
		 * �����ʼ�
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �����û�
	 * 
	 * @return
	 */
	/*public static User load() {
		// TODO Auto-generated method stubtry
		try {
			return UserDao.load();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
	public static List<User> load() {
		// TODO Auto-generated method stubtry
		try {
			return UserDao.load();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ɾ���û�
	 * @param uid
	 * @return
	 */
	public static void   deleteuser(String uid) {
		// TODO Auto-generated method stub
		try {
			UserDao.deleteuser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
