package com.ssafy.homebackend.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.User;

@Mapper
public interface UserMapper {
	ArrayList<User> selectAllUser();

	int register(User user);

	int deleteAccount(String id);

	int modifyAccount(User user);

	User login(User userInput);

	void saveRefreshToken(Map<String, String> map);

	int deleteRefreshToken(Map<String, String> map);
}
