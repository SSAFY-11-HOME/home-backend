package com.ssafy.homebackend.service;
import com.ssafy.homebackend.vo.Board;

public interface BoardQnAService {

	public int insert(Board board);

	public Board selectOne(int articleId);

	public int delete(int articleId);

	public int update(Board board);
}
