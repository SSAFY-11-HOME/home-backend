package com.ssafy.homebackend.service;

import java.util.ArrayList;

import com.ssafy.homebackend.vo.User;

public interface UserService {
	public ArrayList<User> selectAllUser();

	public int register(User user);

	public int deleteAccount(String id);

	public int modifyAccount(User user);

	public User login(User userInput);

	public void saveRefreshToken(String id, String refreshToken);

	public int deleteRefreshToken(String userId);

	public String getRefreshToken(String id);
}
