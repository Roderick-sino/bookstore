<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>添加评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			
			if(!$("#recomment").val()) {
				alert("回复不能为空！");
				return false;
			}
			return true;
		}
		</script>
		<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
<form action="<c:url value='/admin/AdminCommentServlet'/>" method="post" onsubmit="return checkForm()">
    <input type="hidden" name="commentid"  value="${comment.commentid}"/>
     <input type="hidden" name="method" value="editrecomment"/>
     评论：<input type="text"  readonly name="comment" id="comment"  readonly="readonly" value="${comment.comment }"/><br/>
     回复：<input type="text" name = "recomment" id="recomment" />
<input type="submit" value="回复"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
</form>
  </body>
</html>
