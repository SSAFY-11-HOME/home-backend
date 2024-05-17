package com.ssafy.homebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.BoardQnAMapper;
import com.ssafy.homebackend.vo.Board;

@Service
public class BoardQnAServiceImpl implements BoardQnAService {
	@Autowired
	BoardQnAMapper mapper;
	
	@Override
	public int insert(Board board) {
		return mapper.insert(board);
	}

	@Override
	public Board selectOne(int articleId) {
		return mapper.selectOne(articleId);
	}

	@Override
	public int delete(int articleId) {
		return mapper.delete(articleId);
	}

	@Override
	public int update(Board board) {
		return mapper.update(board);
	}
}
