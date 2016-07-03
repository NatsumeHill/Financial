<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Testjson.jsp' starting page</title>

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
	This is my JSP page.
	<br>
	<a id="get">点我</a>
	<br>
	<input type="text" name="income" value="">
	<table id="inout" border="1" cellspacing="0">
		<tr>
			<td>flag</td>
			<td>type</td>
			<td>金额</td>
		</tr>
	</table>
</body>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$("document").ready(function () {
	$.ajax({
		url:"bill/jsonInOut",
		type:"POST",
		dataType:"json",
		success:function(data){
			for(var i=0;i<data.length;i++)
			{
				$("#inout").append(
						"<tr>"+
						"<td>"+data[i].billFlag+"</td>"+
						"<td>"+data[i].billType+"</td>"+
						"<td>"+data[i].billSumMoney+"</td>"+
						"</tr>");
			}
		}
	});
})
</script>
</html>
