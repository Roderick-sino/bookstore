<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书详细</title>

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
.text-area-bottom {
	text-align: right;
	margin: 5px 0px 0px 0px;
	float: right;
	padding: 0px 0px 0px 0px;

}

.text-area-bottom a {
	border: #ebebeb 2px solid;
	padding: 10px 20px 10px 20px;
	text-decoration: none;
	color: #000000;
	font-size: 14px;

}

a:-webkit-any-link {
	color: -webkit-link;
	cursor: pointer;
	text-decoration: underline;

}
</style>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/pager/pager.css'/>" />



<script type="text/javascript"
	src="<c:url value='/jsps/pager/pager.js'/>"></script>
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/book/desc.css'/>">
<link href="css/smohan.face.css" type="text/css" rel="stylesheet">
<script src="<c:url value='/jsps/js/book/desc.js'/>"></script>



</head>

<body>
	<div class="divBookName">${book.bname }</div>
	<div>
		<img align="top" src="<c:url value='/${book.image_w }'/>"
			class="img_image_w" />
		<div class="divBookDesc">
			<ul>
				<li>商品编号：${book.bid }</li>
				<li>价格：<span class="price_n">&yen;${book.currPrice }</span></li>
				<li>定价：<span class="spanPrice">&yen;${book.price }</span> 折扣：<span
					style="color: #c30;">${book.discount }</span>折
				</li>
			</ul>
			<hr class="hr1" />
			<table>
				<tr>
					<td colspan="3">作者：${book.author } 著</td>
				</tr>
				<tr>
					<td colspan="3">出版社：${book.press }</td>
				</tr>
				<tr>
					<td colspan="3">出版时间：${book.publishtime }</td>
				</tr>
				<tr>
					<td>版次：${book.edition }</td>
					<td>页数：${book.pageNum }</td>
					<td>字数：${book.wordNum }</td>
				</tr>
				<tr>
					<td width="180">印刷时间：${book.printtime }</td>
					<td>开本：${book.booksize }开</td>
					<td>纸张：${book.paper }</td>
				</tr>
			</table>
			<div class="divForm">
				<form id="form1" action="<c:url value='/CartItemServlet'/>"
					method="post">
					<input type="hidden" name="method" value="add" /> <input
						type="hidden" name="bid" value="${book.bid }" /> 我要买：<input
						id="cnt" style="width: 40px;text-align: center;" type="text"
						name="quantity" value="1" />件
				</form>
				<a id="btn" href="javascript:$('#form1').submit();"></a>

			</div>
		</div>
		<br>
		<form id="form" action="<c:url value='/CommentServlet?method=add'/>"
			method="post">
			<table rules=none>
				<tr>
					<input id="bid" name="bid" type="hidden" value="${book.bid}" />
					<td><textarea id="comment" name="comment"
							style="width: 100%;height:150;" class="text-area text-area-input"
							style="color: rgb(0, 0, 0);">请输入评论内容......</textarea></td>


				</tr>
				<div id="Smohan_FaceBox">
				<tr>
					<td><a href="javascript:void(0)" class="face" title="表情"></a>
						<span class="text-area-bottom" style="margin-top:12px"><a
							href="javascript:$('#form').submit();">添加</a></span></td>
				</tr>
				</div>
			</table>
		</form>
		<br>
		<table border=2px rules=none>
			<c:forEach items="${commentList}" var="obj">
				<tr>
					<td>======================================================================</td>
				</tr>
				<tr width="530x;">
					<td>匿名评论:&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
						&nbsp&nbsp&nbsp${obj.time}</td>

				</tr>
				<tr width="530x;">
					<td>&nbsp &nbsp ${obj.comment}</td>
				</tr>
				<c:choose>
					<c:when test="${obj.recomment==null}">
					</c:when>
					<c:otherwise>
						<tr>
							<td>----------------------------------------------------------------------</td>
						</tr>
						<tr width="530x;">
							<td>商家回复: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								&nbsp ${obj.time}</td>

						</tr>
						<tr width="530x;">
							<td>&nbsp &nbsp ${obj.recomment}</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>

				</tr>
			</c:forEach>
		</table>
</body>
</html>
<!-- 1图书样式有问题，如何剧中 -->
<!-- 2.高级*2搜索放大
	   3.页面颜色改变
	   4.登录界面css
 -->
