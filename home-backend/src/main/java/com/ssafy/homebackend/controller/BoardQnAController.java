package com.ssafy.homebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.BoardQnAService;
import com.ssafy.homebackend.vo.Board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/qboard")
@CrossOrigin("*")
@Tag(name = "QnA 게시판 컨트롤러", description = "QnA 게시판 관련 요청 처리 클래스")
public class BoardQnAController {
	@Autowired
	BoardQnAService boardQnAService;
	
	
	@Operation(summary = "QnA 게시판 글 쓰기", description = "로그인 상태에서만 글 쓰기 가능. id, title, contents 받음.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "QnA 글쓰기 성공"), 
			@ApiResponse(responseCode = "400", description = "QnA 글쓰기 실패")
			})
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Board board) {
		if (board.getId().equals("") || board.getTitle().equals("") || board.getContents().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		int resultCode = boardQnAService.insert(board);
		System.out.println("result Code = " + resultCode);
		return new ResponseEntity<String>("QnA 작성 성공", HttpStatus.CREATED);
	}
}
