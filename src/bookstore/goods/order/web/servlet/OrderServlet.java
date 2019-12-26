package bookstore.goods.order.web.servlet;

import java.io.Console;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.goods.cart.domain.CartItem;
import bookstore.goods.cart.service.CartItemService;
import bookstore.goods.order.domain.Order;
import bookstore.goods.order.domain.OrderItem;
import bookstore.goods.order.service.OrderService;
import bookstore.goods.pager.PageBean;
import bookstore.goods.user.domain.User;
import bookstore.utils.PaymentUtil;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {

	private OrderService orderService = new OrderService();
	private CartItemService cartItemService = new CartItemService();

	/**
	 * 获取当前页码
	 * 
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	/**
	 * 支付准备
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String paymentPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("order", orderService.load(req.getParameter("oid")));// 查询并保存
		return "f:/jsps/order/pay.jsp";
	}

	/**
	 * 易宝第三方平台支付方法
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String payment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		// 加载配置文件
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		/*
		 * 准备参数
		 */
		String p0_Cmd = "Buy";// 业务类型，
		String p1_MerId = props.getProperty("p1_MerId");// 商号编码，在易宝的唯一标识
		String p2_Order = req.getParameter("oid");// 订单编码
		String p3_Amt = "0.01";// 支付金额（模拟值为1，由于是测试账户。打进去的钱不退回！）
		String p4_Cur = "CNY";// 交易币种，固定值CNY
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = props.getProperty("p8_Url");// 在支付成功后，易宝会访问这个地址。
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 扩展信息
		String pd_FrpId = req.getParameter("yh");// 支付通道
		String pr_NeedResponse = "1";// 应答机制，固定值1

		/*
		 * 计算hmac（签名数据）——详见易宝帮助文档。 需要以上13个参数 需要keyValue 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		/*
		 * hmac算法
		 */
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);

		resp.sendRedirect(sb.toString());
		return null;
	}
/**
 * 回馈界面
 * @param req
 * @param resp
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String back(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 获取12个参数
		 */
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		//获取keyValue

		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		String keyValue = props.getProperty("keyValue");
		/* 调用PaymentUtil的校验方法来校验调用者的身份
		 *   如果校验失败：保存错误信息，转发到msg.jsp
		 *   */
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
				r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType,
				keyValue);
		if(!bool) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "无效的签名，支付失败！");
			return "f:/jsps/msg.jsp";
		}
		if(r1_Code.equals("1")) {
			orderService.updateStatus(r6_Order, 2);
			if(r9_BType.equals("1")) {
				req.setAttribute("code", "success");
				req.setAttribute("msg", "恭喜，支付成功！");
				return "f:/jsps/msg.jsp";				
			} else if(r9_BType.equals("2")) {
				resp.getWriter().print("success");
			}
		}
		return null;
	}
	
	
	public String back1(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String order=req.getParameter("order");
		orderService.updateStatus(order, 2);
		req.setAttribute("code", "success");
		req.setAttribute("msg", "恭喜，支付成功！");
		return "f:/jsps/msg.jsp";	
	}
	public String back2(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	req.setAttribute("code", "error");
	req.setAttribute("msg", "无效的签名，支付失败！");
	return "f:/jsps/msg.jsp";
	}
	/**
	 * 截取url，
	 * 
	 * @param req
	 * @return
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * 加载订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");// 超链接访问本方法的
		req.setAttribute("btn", btn);
		return "f:/jsps/order/desc.jsp";
	}

	/**
	 * 我的订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		// 从当前session中获取User
		User user = (User) req.getSession().getAttribute("sessionUser");
		PageBean<Order> pb = orderService.myOrders(user.getUid(), pc);

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/order/list.jsp";
	}

	public String createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取所有购物车的id
		String cartItemIds = req.getParameter("cartItemIds");
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		// 创建order
		Order order = new Order();
		order.setOid(CommonUtils.uuid());// 设置主键
		order.setOrdertime(String.format("%tF %<tT", new Date()));// 下单时间
		order.setStatus(1);// 设置状态，1表示未付款
		order.setAddress(req.getParameter("address"));// 设置收货地址
		System.out.println("地址为"+req.getParameter("address"));
		User owner = (User) req.getSession().getAttribute("sessionUser");
		order.setOwner(owner);// 设置订单所有者

		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : cartItemList) {
			total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
		}
		order.setTotal(total.doubleValue());// 设置总计
		// 创建orderItem
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(CommonUtils.uuid());// 设置主键
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		// 添加
		orderService.createOrder(order);

		// 删除购物车条目(当加入订单时，清空购物车所选项）
		cartItemService.batchDelete(cartItemIds);
		req.setAttribute("order", order);
		return "f:/jsps/order/ordersucc.jsp";
	}

	/**
	 * 取消订单状态
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if (status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "您的操作失误，状态有误，不能取消！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid, 5);// 设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已取消，请确认！");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 确认收货
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if (status != 3) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能确认收货！");
			return "f:/jsps/msg.jsp";
		}
		System.out.print("123");
		orderService.updateStatus(oid, 4);// 设置状态为交易成功！
		req.setAttribute("code", "success");
	///	System.out.print("333");
		req.setAttribute("msg", "恭喜，交易成功！");
	//	System.out.print("456");
		return "f:/jsps/msg.jsp";
	}
	
	
	public String comment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		return "f:jsps/book/comment.jsp";
	}
	
}
