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
    
    <title>评论列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">  
	--><link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/category/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">

  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
   
    	<tr class="trTitle">
    		<th>评论</th>
    		<th>回复</th>
    		<th>操作</th>
    	</tr>
    	
<c:forEach items="${comment }" var="obj">    	
    	<tr class="trOneLevel">
    	<input type="hidden" id="commentid" name="commentid" value="${obj.commentid }"/>
    		<td width="200px;">${obj.comment}</td>
    		<td width="200px;">${obj.recomment}</td>
    		
    	
    		<td width="200px;">

    		  <a href="<c:url value='/admin/AdminCommentServlet?method=editrecommentPre&commentid=${obj.commentid }'/>">回复</a>
    		  <a onclick="return confirm('您是否真要删除评论吗？')" href="<c:url value='/admin/AdminCommentServlet?method=delete&commentid=${obj.commentid }'/>">删除</a>
    		</td>
    	</tr>

   </c:forEach>


    </table>
  </body>
</html>