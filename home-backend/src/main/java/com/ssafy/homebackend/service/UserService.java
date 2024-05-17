package com.ssafy.homebackend.service;

import java.util.ArrayList;

import com.ssafy.homebackend.vo.User;

public interface UserService {
	public ArrayList<User> selectAllUser();

	public int register(User user);

	public int deleteAccount(String id);
}
