<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'list.jsp' starting page</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form action="modifyBill" method="post">
		<input name="billId" value="${bill.billId}" style="display: none">
		金额：&nbsp; <input type="text" name="billMoney" value="${bill.billMoney}"> <br>
		收入支出：&nbsp; <input type="text" name="billType" value="${bill.billType}"><br>
		标签：&nbsp; <input type="text" name="billFlag" value="${bill.billFlag}"> <br>
		别人可见：&nbsp; <input type="text" name="isHide" value="${bill.isHide}"> <br> <input
			type="submit" value="提交">
	</form>
</body>
</html>

