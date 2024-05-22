package com.ssafy.homebackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.Board;
import com.ssafy.homebackend.vo.Comment;

@Mapper
public interface BoardQnAMapper {
	int insert(Board board);

	Board selectOne(int articleId);

	int delete(int articleId);

	int update(Board board);

	ArrayList<Board> getPrevNext(int articleId);

	int getPrevId(int articleId);

	int getNextId(int articleId);

	ArrayList<Board> selectAll();

	int addCount(int articleId);

	ArrayList<Comment> getComments(int articleId);

	int writeComment(Comment comment);

	int deleteComment(Comment comment);
}
