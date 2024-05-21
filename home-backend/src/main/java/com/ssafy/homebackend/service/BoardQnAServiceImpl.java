package com.ssafy.homebackend.service;

import java.util.ArrayList;

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

	@Override
	public Board getPrev(int articleId) {
		int prevId = mapper.getPrevId(articleId);
		return mapper.selectOne(prevId);
	}

	@Override
	public Board getNext(int articleId) {
		int nextId = mapper.getNextId(articleId);
		return mapper.selectOne(nextId);
	}

	@Override
	public ArrayList<Board> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int addCount(int articleId) {
		return mapper.addCount(articleId);
	}
}
