<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
  </head>
  <body>
 		<a href="goaddBill">新增</a>
 		<table border="1" cellspacing="0">
  		<tr>
  			<td>类型</td>
  			<td>收入/支出</td>
  			<td>金额</td>
  			<td>时间</td>
  		</tr>
  		<c:choose>
  			<c:when test="${bills == null}">
  				<h1>没有数据</h1>
  			</c:when>
  			<c:otherwise>
  				<c:forEach items="${bills}" var="v">
  					<tr>
  						<td>${v.billFlag }</td>
  						<td>${v.billType }</td>
  						<td>${v.billMoney }</td>
  						<td>${v.billDate }</td>
  						<td>
  							<a href="goModify?billId=${v.billId }">修改</a>|
  							<a href="delBill?billId=${v.billId }">删除</a>
  						</td>
  					</tr>
  				</c:forEach>
  			</c:otherwise>
  		</c:choose>
  	</table>
  </body>
</html>
  						
