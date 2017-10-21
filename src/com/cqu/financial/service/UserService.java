package com.cqu.financial.service;

import javax.servlet.http.HttpServletRequest;

import com.cqu.financial.entity.User;

public interface UserService {
	public User checkUser(String userName,String Pass);
	public boolean addUser(User user);
	public boolean isExistUserName(String userName);
	public User findById(HttpServletRequest request);
}
