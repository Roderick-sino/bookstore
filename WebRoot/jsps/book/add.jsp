<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript">


		function checkForm() {
			
			if(!$("#comment").val()) {
				alert("评论不能为空！");
				return false;
			}
			return true;
		}
		
		function getQueryVariable(variable)
	 {
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}
		</script>
		
  </head>
  
  <body>
  <form action="<c:url value='/CommentServlet'/>" method="post" onsubmit="return checkForm()">
     <input type="hidden" name="method" value="add1"/>
     <input type="hidden" name="bid" value="${bid }"/>
 <table align="center" border="1" cellpadding="0" cellspacing="0" width=900px height=200px>
   <tr align="center"><td colspan="4">订单号：${oid }
    	<tr class="trTitle">
    		<th>书编号</th>
    		<th>书名</th>
    		<th>评论</th>
    		<th>操作</th>
    		
    	</tr>
    	

    	<tr class="trOneLevel">
    	<td width="100px;" align="center" >
  ${bid }
  </td>  <td width="100px;" align="center" weight=70% style="width: 511px; ">
 ${book.bname}
  </td>
  <td width="200px;" align="center" >
  <input type="textarea"   name="comment" id="comment"  value=""/><br/>
  </td>
  <td width="200px;" align="center" >
     <input type="submit" value="评论"/>
 
  </td>
  </tr>


    </table>
    </form>
  </body>
</html>