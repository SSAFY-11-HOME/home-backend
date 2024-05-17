package com.ssafy.homebackend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.UserService;
import com.ssafy.homebackend.vo.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(name = "유저 컨트롤러", description = "회원정보 관리(가입, 수정, 탈퇴), 로그인, 로그아웃, 토큰 처리 등 회원 인증 관련 요청 처리 클래스")
public class UserController {
	@Autowired
	UserService userService;

	@Operation(summary = "회원가입", description = "id, pw, name, email, isBroker(중개업자인가요? 체크박스 등)를 받아 유저 정보 생성. 가입시간 자동 입력. admin은 관리자 페이지에서 admin으로 지정해주는 식으로 처리")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "회원정보 추가 성공"),
			@ApiResponse(responseCode = "400", description = "회원 가입 시 필요한 정보 누락") 
			})
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (user.getId().equals("") || user.getPw().equals("") || user.getName().equals("")
				|| user.getEmail().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		int resultCode = userService.register(user);
		System.out.println("result Code = " + resultCode);
		return new ResponseEntity<String>("회원가입 성공", HttpStatus.CREATED);
	}

	@Operation(summary = "회원탈퇴", description = "id에 해당하는 계정 삭제")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "회원탈퇴 성공"),
			@ApiResponse(responseCode = "400", description = "회원탈퇴 실패")
			})
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAccount(@RequestBody String id) {
		int resultCode = userService.deleteAccount(id);
		System.out.println("result Code = " + resultCode);
		if (resultCode == 1) {
			return new ResponseEntity<String>("회원탈퇴 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("회원탈퇴 실패", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "회원정보 수정", description = "파라미터 4개(id, pw, name, email). User.id에 해당하는 회원정보 수정. pw, name, email만 수정 가능")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "회원정보 수정 성공"), 
			})
	@PutMapping("/modify")
	public ResponseEntity<String> modifyAccount(@RequestBody User user) {
		if (user.getId().equals("") || user.getPw().equals("") || user.getName().equals("")
				|| user.getEmail().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		int resultCode = userService.modifyAccount(user);
		System.out.println("result Code = " + resultCode);
		return new ResponseEntity<String>("회원정보 수정 성공", HttpStatus.OK);
	}

//	@Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(
//			@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		try {
//			MemberDto loginUser = memberService.login(memberDto);
//			if(loginUser != null) {
//				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
//				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
//				
////				발급받은 refresh token 을 DB에 저장.
//				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);
//				
////				JSON 으로 token 전달.
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
//				
//				status = HttpStatus.CREATED;
//			} else {
//				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
//				status = HttpStatus.UNAUTHORIZED;
//			} 
//			
//		} catch (Exception e) {
//			log.debug("로그인 에러 발생 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}

	// Authentication : 인증 (사용자 인지)
//	principal: 아이디 (username)
//	credential: 비밀번호 (password)

	// Authorization : 인가 (권한 있는지)

	@GetMapping("/all")
	public ResponseEntity<ArrayList<User>> selectAllUser() {
		ArrayList<User> list = userService.selectAllUser();
		return ResponseEntity.ok(list);
	}
}