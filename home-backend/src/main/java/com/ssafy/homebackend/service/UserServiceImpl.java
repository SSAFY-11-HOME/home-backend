package com.ssafy.homebackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.UserMapper;
import com.ssafy.homebackend.vo.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper mapper;

	@Override
	public ArrayList<User> selectAllUser() {
		return mapper.selectAllUser();
	}

	@Override
	public int register(User user) {
		return mapper.register(user);
	}

	@Override
	public int deleteAccount(String id) {
		return mapper.deleteAccount(id);
	}

	@Override
	public int modifyAccount(User user) {
		return mapper.modifyAccount(user);
	}

	@Override
	public User login(User userInput) {
		return mapper.login(userInput);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public int deleteRefreshToken(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userId);
		map.put("token", null);
		return mapper.deleteRefreshToken(map);
	}
}
