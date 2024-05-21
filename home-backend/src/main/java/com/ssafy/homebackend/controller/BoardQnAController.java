package com.ssafy.homebackend.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.BoardQnAService;
import com.ssafy.homebackend.vo.Board;
import com.ssafy.homebackend.vo.SearchIdName;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "QnA 게시판 글 읽기", description = "권한 상관 없이 글 읽기 가능. 글 번호 파라미터로 받음. map으로 현재글(current), 이전(prev), 이후(next) 글에 대한 정보 반환")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "QnA 글 읽기 성공")
			})
	@GetMapping("/{articleId}")
	public ResponseEntity<HashMap<String, Board>> selectOne(@Parameter(description = "글 번호") @PathVariable int articleId) {
		HashMap<String, Board> map = new HashMap<>();

		// 조회수 증가
		int result = boardQnAService.addCount(articleId);
		System.out.println("조회수 증가 결과 : "+result);
		
		// 현재 글
		Board current = boardQnAService.selectOne(articleId);
		map.put("current", current);
				
		// 이전 글 (없으면 null)
		Board prev = boardQnAService.getPrev(articleId);
		map.put("prev", prev); 
		
		// 이후 글 (없으면 null)
		Board next = boardQnAService.getNext(articleId);
		map.put("next", next);
		
		return new ResponseEntity<HashMap<String, Board>>(map, HttpStatus.OK);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Operation(summary = "QnA 게시판 글 삭제", description = "자기 글만 삭제 가능. id, articleId 받음")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "QnA 글 삭제 성공"),
			@ApiResponse(responseCode = "400", description = "QnA 글 삭제 실패")
			})
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Board board) {
		String id = board.getId();
		int articleId = board.getArticleId();
		
		Board temp = boardQnAService.selectOne(articleId);
		if(temp.getId().equals(id)==false) {
			return new ResponseEntity<String>("자신의 글만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST);
		}
		
		int result = boardQnAService.delete(articleId);
		return new ResponseEntity<String>("글 삭제 성공", HttpStatus.OK);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "QnA 게시판 글 수정", description = "자기 글만 수정 가능. articleId, id, title, contents 받음")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "QnA 글 수정 성공"),
			@ApiResponse(responseCode = "400", description = "QnA 글 수정 실패")
			})
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Board board) {
		if (board.getTitle().equals("") || board.getContents().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		
		String id = board.getId();
		int articleId = board.getArticleId();
		
		
		Board temp = boardQnAService.selectOne(articleId);
		if(temp.getId().equals(id)==false) {
			return new ResponseEntity<String>("자신의 글만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST);
		}
		
		int result = boardQnAService.update(board);
		return new ResponseEntity<String>("글 수정 성공", HttpStatus.OK);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "QnA 게시판 글 전체 불러오기", description = "권한 상관 없이 글 읽기 가능.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "QnA 글 불러오기 성공")
			})
	@GetMapping("/list")
	public ResponseEntity<ArrayList<Board>> selectAll() {
		
//		log.info("게시판 글 불러오기!");
		ArrayList<Board> list = boardQnAService.selectAll();
		
		return new ResponseEntity<ArrayList<Board>>(list, HttpStatus.OK);
	}
}
