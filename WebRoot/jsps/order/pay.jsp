<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pay.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/pay.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("img").click(function() {
		$("#" + $(this).attr("name")).attr("checked", true);
	});
});
</script>
  </head>
  
  <body>
<div class="divContent">
	<span class="spanPrice">支付金额：</span><span class="price_t">&yen;${order.total }</span>
	<span class="spanOid">编号：${order.oid }</span>
</div>
<form action="<c:url value='/OrderServlet'/>" method="post" id="form1" target="_top">
<input type="hidden" name="method" value="payment"/>
<input type="hidden" name="oid" value="${order.oid }"/>
<div class="divBank">
	<div class="divText">选择网上银行</div>
	<div style="margin-left: 20px;">
	  <div style="margin-bottom: 20px;">
		<input id="ICBC-KUAICREDIT" type="radio" name="yh" value="ICBC-KUAICREDIT" checked="checked"/>
		<img name="ICBC-NET-B2C" align="middle" src="<c:url value='/bank_img/icbc.bmp'/>"/>
		
		
		<input id="ABC-KUAICREDIT" type="radio" name="yh" value="ABC-KUAICREDIT"/>
		<img name="ABC-NET-B2C" align="middle" src="<c:url value='/bank_img/abc.bmp'/>"/>
		
		<input id="CCB-KUAICREDIT" type="radio" name="yh" value="CCB-KUAICREDIT"/>
		<img name="CCB-NET-B2C" align="middle" src="<c:url value='/bank_img/ccb.bmp'/>"/>
	  
		

	

		

	
	
		<input id="CMBC-KUAICREDIT" type="radio" name="yh" value="CMBC-KUAICREDIT"/>
		<img name="CMBC-NET-B2C" align="middle" src="<c:url value='/bank_img/cmbc.bmp'/>"/>

		  </div>
	  <div style="margin-bottom: 20px;">

		<input id="BOC-KUAICREDIT" type="radio" name="yh" value="BOC-KUAICREDIT"/>
		<img name="BOC-NET-B2C" align="middle" src="<c:url value='/bank_img/bc.bmp'/>"/>

		<input id="CPB-KUAICREDIT" type="radio" name="yh" value="CPB-KUAICREDIT"/>
		<img name="PINGANBANK-NET" align="middle" src="<c:url value='/bank_img/pingan.bmp'/>"/>

		
		
		<input id="ECITIC-KUAICREDIT" type="radio" name="yh" value="ECITIC-KUAICREDIT"/>
		<img name="ECITIC-NET-B2C" align="middle" src="<c:url value='/bank_img/zx.bmp'/>"/>



		<input id="SPDB-KUAICREDIT" type="radio" name="yh" value="SPDB-KUAICREDIT"/>
		<img name="SPDB-NET-B2C" align="middle" src="<c:url value='/bank_img/shpd.bmp'/>"/>
	
	 
		
	     </div>
	     <a href="javascript:void $('#form1').submit();" class="linkNext">下一步</a>
	      <a href="<c:url value='/OrderServlet?method=back1&order=${order.oid }'/>">支付成功</a>
	      	      <a href="<c:url value='/OrderServlet?method=back2'/>">支付失败</a>
	</div>
</div>
</form>
  </body>
</html>
