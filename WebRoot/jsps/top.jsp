<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	background: rgb(78,78,78);
	margin: 0px;
	color: #ffffff;
}

a {
	text-decoration: none;
	color: #ffffff;
	font-weight: 900;
}

a:hover {
	text-decoration: underline;
	color: #ffffff;
	font-weight: 900;
}
</style>
</head>

<body>
	<h1 style="text-align: center;">BookStore图书销售系统</h1>
	<div style="font-size: 10pt; line-height: 10px;">
		<%--根据用户是登录，显示不同的链接 --%>
		<c:choose>
			<c:when test="${empty sessionScope.sessionUser }">
				<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">会员登录</a> |&nbsp; 
		  <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册会员</a>
			</c:when>
			<c:otherwise>
		      会员：${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		   <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <!-- session.clear(); -->
		  <a href="<c:url value='/UserServlet?method=out'/>" target="_parent" >退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="https://www.gench.edu.cn/" target="_top">联系我们</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
