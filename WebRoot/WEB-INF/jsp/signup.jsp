<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />

<link rel="stylesheet" href="<%=basePath%>css/sign-style.css"
	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/min_css.css" />

<title></title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="picture-panel">
				<div class="pict">
					<img src="<%=basePath%>image/bground.png" height="100%"
						width="100%" />
				</div>
			</div>

			<div class="sign-panel">
				<div class="sign-C">
					<div class="sign-head">
						<h2 class="title-form">注册新用户</h2>
					</div>

					<div class="sign-body">
						<form action="signUp.do" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" name="userName" placeholder="请输入账号"
										id="username" type="text" />
									<div id="worngName"></div>
								</div>

								<div class="form-group">
									<input class="form-control" name="userPass" type="password" />
									<div id="worngPass"></div>
								</div>

								<div class="form-group">
									<input class="form-control" name="cuserPass" type="password"
										value="" />
									<div id="worngcPass"></div>
								</div>

								<div class="form-group"></div>
								<input type="submit" id="signUp" value="注册" class="btn" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	/**
	 * 绑定事件
	 */
	$("document").ready(
			function() {
				$("#username").blur(
						function() {
							if ($("#username").val() == "")
								$("#worngName").css('visibility', 'visible');
							else
								$.ajax({
									url : "validUserName",
									type : "POST",
									data : {
										"username" : $("#username").val()
									},
									dataType : "text",
									success : function(data) {
										if (data == "true")
											$("#worngName").css('visibility',
													'visible');
										else
											$("#worngName").css('visibility',
													'hidden');
									}
								});
						});
				$("input[name='cuserPass']").blur(
						function(event) {
							if ($("input[name='cuserPass']").val() != $(
									"input[name='userPass']").val())
								$("#worngcPass").css('visibility', 'visible');
							else
								$("#worngcPass").css('visibility', 'hidden');
						});
				$("input[name='userPass']").blur(function(event) {
					if ($("input[name='userPass']").val().length < 6)
						$("#worngPass").css('visibility', 'visible');
					else
						$("#worngPass").css('visibility', 'hidden');
				});
			});
</script>
</html>