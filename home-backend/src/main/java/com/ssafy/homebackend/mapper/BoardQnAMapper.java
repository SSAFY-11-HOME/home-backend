package com.ssafy.homebackend.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.Board;

@Mapper
public interface BoardQnAMapper {
	int insert(Board board);
}
