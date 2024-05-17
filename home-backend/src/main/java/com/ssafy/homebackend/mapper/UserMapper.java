package com.ssafy.homebackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.User;

@Mapper
public interface UserMapper {
	ArrayList<User> selectAllUser();

	int register(User user);

	int deleteAccount(String id);
}
