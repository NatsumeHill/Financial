<%@ page language="java" import="java.util.*,com.cqu.financial.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="generator" content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
  <link rel="stylesheet" href="<%=basePath %>css/style.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/min_css.css" />

  <title></title>
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="picture-panel">
        <div class="pict"><img src="<%=basePath %>image/bground.png" height="100%" width="100%" /></div>
      </div>

      <div class="login-panel">
        <div class="login-C">
          <div class="login-head">
            <h2 class="title-form">登录</h2>
          </div>

          <div class="panel-body">
            <form  action="check" method="post">
              <fieldset>
              
                <div class="form-group">
                  <input class="form-control"  name="userName" type="text"  />
                </div>

                <div class="form-group">
                  <input class="form-control"  name="userPass" type="password"  />
                </div>

                <div class="form-group">
                  <a class="form-control2">忘记密码？</a> 
                  <a href="goToSignUp.do" class="form-control2">还没有账户？</a>
                </div><input type ="submit" value = "登录" class="btn" />
               
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
