<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
    <script type="text/javascript">
    </script>
  </head>
  <body>
	<form action="addemp.do" method="post"> 
		姓名：&nbsp;<input type="text" style="margin: 3px" name="ename"><br/>
		职业：&nbsp;<input type="text" style="margin: 3px" name="job"><br/>
		<input type="submit" value="提交">
	</form>
  </body>
</html>
