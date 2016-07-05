package com.cqu.financial.interceptor;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		Object user = request.getSession().getAttribute("user");
		ServletContext sc = request.getSession().getServletContext();
		if (user != null) {
			return true;
		}
		
		response.setStatus(302);
		PrintWriter out = response.getWriter();
		out.println("<html> <script>  parent.location.href ='" + sc.getContextPath()
				+ "/pages/login.html'; </script> </html>");
		//response.sendRedirect(sc.getContextPath() + "/pages/login.html");
		return false;
	}

}
