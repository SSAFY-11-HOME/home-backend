package com.ssafy.homebackend.service;
import java.util.ArrayList;

import com.ssafy.homebackend.vo.Board;

public interface BoardQnAService {

	public int insert(Board board);

	public Board selectOne(int articleId);

	public int delete(int articleId);

	public int update(Board board);

	public Board getPrev(int articleId);
	
	public Board getNext(int articleId);

	public ArrayList<Board> selectAll();
}
