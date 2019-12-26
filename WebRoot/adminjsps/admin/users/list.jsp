<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2 style="text-align: center;">用户列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    <tr class="trTitle">
    		<th>用户名</th>
    		<th>用户id</th>
    		<th>操作</th>
    	</tr>
    <c:forEach items="${userList }" var="obj">    	
    	<tr class="trOneLevel">
    		<td width="200px;">${obj.uid}</td>
            <td style="text-align: center;" width="200px;">${obj.loginname}</td>
    		<td style="text-align: center;" width="200px;">
    		   <a onclick="return confirm('您是否真要删除该用户吗？')" href="<c:url value='/admin/AdminUserServlet?method=deleteuser&uid=${obj.uid }'/>">删除</a>
    		</td>
    	</tr>
    </c:forEach>
    	
    </table>
  </body>
</html>
