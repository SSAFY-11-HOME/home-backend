package com.ssafy.homebackend.service;

import java.util.ArrayList;

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
}
