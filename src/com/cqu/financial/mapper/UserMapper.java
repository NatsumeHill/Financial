package com.cqu.financial.mapper;

import com.cqu.financial.entity.User;

public interface UserMapper {
	public User getUserByName(String userName);
	public int addUser(User user);
}
