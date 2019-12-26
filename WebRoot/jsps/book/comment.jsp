<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">

	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/list.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>

  </head>
  
  <body>
  <input type="button" onclick="javascript:window.location.reload()" value="刷新"/>
  <br>		<span style="margin-right: 50px;"><a target="_top" href="<c:url value='/index.jsp'/>">主页</a></span>
 <table align="center" border="1" cellpadding="0" cellspacing="0" align="center" border="1" cellpadding="0" cellspacing="0" width=900px height=200px>
   <tr><td colspan="3">订单号：${order.oid }
  　下单时间：${order.ordertime }</td></tr>
    	<tr class="trTitle">
    		<th>书编号</th>
    		<th>书名</th>
    		<th>操作</th>
    	</tr>
    	
  <c:forEach items="${order.orderItemList }" var="item">

    	<tr class="trOneLevel">
    	<td>
  ${item.book.bid }
  </td>  <td>
 ${item.book.bname}
 
  </td>
  <td>
  
  <c:if test="${item.status eq 0}">


   <a href="<c:url value='/CommentServlet?method=add1Pre&oid=${order.oid }&bid=${item.book.bid }'/>">评价
   </a>
   </c:if>
     <c:if test="${item.status eq 1}">
     已评论
     </c:if>
  </td>
  </tr>
  
</c:forEach>
    </table>
  </body>
</html>