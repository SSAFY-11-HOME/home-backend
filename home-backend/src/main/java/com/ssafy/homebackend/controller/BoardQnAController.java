package com.ssafy.homebackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.ssafy.homebackend.util.JWTUtil;
import com.ssafy.homebackend.vo.Board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/qboard")
@CrossOrigin("*")
@Tag(name = "QnA 게시판 컨트롤러", description = "QnA 게시판 관련 요청 처리 클래스")
public class BoardQnAController {
	@Autowired
	BoardQnAService boardQnAService;
	@Autowired
	JWTUtil jwtUtil;
	
	@Operation(summary = "QnA 게시판 글 쓰기", description = "로그인 상태에서만 글 쓰기 가능. title, contents 받음.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "QnA 글쓰기 성공"), 
			@ApiResponse(responseCode = "400", description = "제목 또는 내용의 길이가 0임."),
			@ApiResponse(responseCode = "401", description = "accessToken 만료됨. /user/refresh로 refreshToken 전달해서 accessToken 갱신 후 재요청."),
			@ApiResponse(responseCode = "404", description = "전달 받은 accessToken 없음. 로그인 필요. 로그인 페이지로 이동시키기.")
			})
	@PostMapping
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Board board, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		// 액세스 토큰 자체가 없는 경우 == 로그인x 상태
		String accessToken = request.getHeader("Authorization");
		if(accessToken == null) {
			System.out.println("토큰 없음. 로그인x 상태.");
			resultMap.put("message", "전달받은 accessToken 없음! 로그인 하세요.");
			status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
			
		// 액세스 토큰은 넘어 온 상태. 해당 토큰 유효한 지 검사.
		if (!jwtUtil.checkToken(accessToken)) {
			System.out.println("accessToken 만료됨.");
			resultMap.put("message", "accessToken 만료됨. /user/refresh 로 토큰 갱신 후 재요청.");
			status = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
			
		// 유효한 액세스 토큰. 토큰에서 id 추출해서 작성한 글 객체에 추가.
		board.setId(jwtUtil.getUserId(accessToken));	
		if (board.getTitle().trim().length()>0 && board.getContents().trim().length()>0) {
			int resultCode = boardQnAService.insert(board);
			resultMap.put("message", "QnA 작성 성공.");
			status = HttpStatus.CREATED;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
			
		} else {
			resultMap.put("message", "제목과 내용 모두 입력해 주세요.");
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
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
	@Operation(summary = "QnA 게시판 글 삭제", description = "자기 글만 삭제 가능. Header(accessToken), Body(id, articleId) 받음")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "QnA 글 삭제 성공"),
			@ApiResponse(responseCode = "400", description = "accessToken에서 추출한 id와 글의 id가 다름. 자신의 글만 삭제 가능."),
			@ApiResponse(responseCode = "401", description = "accessToken 만료됨. /user/refresh로 refreshToken 전달해서 accessToken 갱신 후 재요청."),
			@ApiResponse(responseCode = "404", description = "전달 받은 accessToken 없음. 로그인 필요. 로그인 페이지로 이동시키기.")
			})
	@DeleteMapping
	public ResponseEntity<Map<String, Object>> delete(@RequestBody Board board, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		// 액세스 토큰 자체가 없는 경우 == 로그인x 상태
		String accessToken = request.getHeader("Authorization");
		if(accessToken == null) {
			System.out.println("토큰 없음. 로그인x 상태.");
			resultMap.put("message", "전달받은 accessToken 없음! 로그인 하세요.");
			status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		
		// 액세스 토큰은 넘어 온 상태. 해당 토큰 유효한 지 검사.
		if (!jwtUtil.checkToken(accessToken)) {
			System.out.println("accessToken 만료됨.");
			resultMap.put("message", "accessToken 만료됨. /user/refresh 로 토큰 갱신 후 재요청.");
			status = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		
		// 유효한 액세스 토큰. 토큰에서 id 추출해서 현재 유저가 작성한 글인지 판단
		String idInToken = jwtUtil.getUserId(accessToken);	
		if (board.getId().equals(idInToken) || board.getId().equals("admin")) { // 현재 유저가 작성한 글이거나 admin 이면 삭제 가능
			int result = boardQnAService.delete(board.getArticleId());
			resultMap.put("message", "QnA 삭제 성공.");
			status = HttpStatus.OK;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
			
		} else {
			resultMap.put("message", "삭제 권한이 없습니다. 현재 로그인 한 사용자가 작성한 글이 아닙니다.");
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
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
