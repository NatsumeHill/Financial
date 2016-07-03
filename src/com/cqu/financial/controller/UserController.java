package com.cqu.financial.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cqu.financial.entity.User;
import com.cqu.financial.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/goindex")
	public String goAddemp() {
		return "index";
	}
	@RequestMapping("/gologin")
	public String  gologin(){
		return "login";
	}
	
	
	@RequestMapping("/check")
	@ResponseBody
	public boolean gocheck(User user,HttpSession httpSession) {
		User tempuser = userService.checkUser(user.getUserName(), user.getUserPass());
		if (tempuser != null) {

			httpSession.setAttribute("user", tempuser);
			return true;
		}
		return false;
	}
	
	@RequestMapping("/goToSignUp")
	public String goSignUp(){
		return "signup";
	}
	@RequestMapping("/signUp")
	public String signUp(String userName, String userPass) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPass(userPass);
		UUID uuid = UUID.randomUUID();
		user.setUserID(uuid.toString().replaceAll("-", ""));
		if(userService.addUser(user)) return "welcome";
		return "404";
		
		
	}
	
	@RequestMapping("/test")
	public ModelAndView testSession(HttpSession httpSession){
	    Object user = httpSession.getAttribute("user");
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "validUserName", method = RequestMethod.POST)
	public void validUserName(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(userService.isExistUserName(request.getParameter("username")));
		} catch (IOException e) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

}
