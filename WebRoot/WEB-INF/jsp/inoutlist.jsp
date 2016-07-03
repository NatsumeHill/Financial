<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellspacing="0">
		<tr>
			<td>类型</td>
			<td>收入/支出</td>
			<td>金额</td>
		</tr>
		<c:choose>
			<c:when test="${list == null}">
				<h1>没有数据</h1>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="v">
					<tr>
						<td>${v.billFlag }</td>
						<td>${v.billType }</td>
						<td>${v.billSumMoney }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>