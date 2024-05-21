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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.UserService;
import com.ssafy.homebackend.util.JWTUtil;
import com.ssafy.homebackend.vo.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(name = "유저 컨트롤러", description = "회원정보 관리(가입, 수정, 탈퇴), 로그인, 로그아웃, 토큰 처리 등 회원 인증 관련 요청 처리 클래스")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	JWTUtil jwtUtil;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "회원가입", description = "id, pw, name, email, isBroker(중개업자인가요? 체크박스 등)를 받아 유저 정보 생성. 가입시간 자동 입력. admin은 관리자 페이지에서 admin으로 지정해주는 식으로 처리")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "회원정보 추가 성공"),
			@ApiResponse(responseCode = "400", description = "회원 가입 시 필요한 정보 누락") })
	@PostMapping
	public ResponseEntity<String> register(@RequestBody User user) {
		if (user.getId().equals("") || user.getPw().equals("") || user.getName().equals("")
				|| user.getEmail().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		int resultCode = userService.register(user);
		System.out.println("result Code = " + resultCode);
		return new ResponseEntity<String>("회원가입 성공", HttpStatus.CREATED);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "회원탈퇴", description = "id에 해당하는 계정 삭제")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원탈퇴 성공"),
			@ApiResponse(responseCode = "400", description = "회원탈퇴 실패"),
			@ApiResponse(responseCode = "500", description = "서버 에러") })
	@DeleteMapping
	public ResponseEntity<String> deleteAccount(@RequestBody User user) {
		String id = user.getId();
		int resultCode = userService.deleteAccount(id);
		System.out.println("result Code = " + resultCode);
		if (resultCode == 1) {
			return new ResponseEntity<String>("회원탈퇴 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("회원탈퇴 실패", HttpStatus.BAD_REQUEST);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "회원정보 수정", description = "파라미터 4개(id, pw, name, email). User.id에 해당하는 회원정보 수정. pw, name, email만 수정 가능")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원정보 수정 성공"), })
	@PutMapping
	public ResponseEntity<String> modifyAccount(@RequestBody User user) {
		if (user.getId().equals("") || user.getPw().equals("") || user.getName().equals("")
				|| user.getEmail().equals("")) {
			return new ResponseEntity<String>("모든 필드를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
		int resultCode = userService.modifyAccount(user);
		System.out.println("result Code = " + resultCode);
		return new ResponseEntity<String>("회원정보 수정 성공", HttpStatus.OK);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "로그인", description = "id와 pw를 전달받아 db에 저장된 계정인지 확인 후 존재하는 계정이면 access-token, refresh-token 반환.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "로그인(토큰 생성 성공)"),
			@ApiResponse(responseCode = "401", description = "로그인 실패(존재하지 않는 계정)"),
			@ApiResponse(responseCode = "500", description = "서버 에러") })
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User userInput) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED; // 초기화(사용x 값)
		try {
			User loginUser = userService.login(userInput);

			// 존재하는 계정인 경우 토큰 생성, db에 토큰값 추가, 토큰 반환
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getId());

//				발급받은 refresh token을 DB에 저장.
				userService.saveRefreshToken(loginUser.getId(), refreshToken);

//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				status = HttpStatus.CREATED; // 토큰 생성됨

				// 존재하지 않는 계정인 경우 -> 401 에러
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "로그아웃", description = "로그인 상태에서 id를 전달받아 해당 유저의 Token값을 null 로 변경")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "로그아웃 성공 (DB에서 토큰값 삭제)"),
			@ApiResponse(responseCode = "500", description = "서버 에러") })
	@PostMapping("/logout")
//	@Hidden
	public ResponseEntity<Map<String, Object>> logout(
			@RequestBody @Parameter(description = "로그아웃 할 회원(자신)의 아이디.", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String userId = user.getId();
		try {
			int result = userService.deleteRefreshToken(userId);
			System.out.println("logout result : " + result);
			resultMap.put("message", "로그아웃 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Operation(summary = "Access Token 만료 시 재발급", description = "body에 id, header에 refreshToken을 담아 전달. refreshToken이 만료되지 않았다면 access-token을 재발급 받는다.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "refreshToken 유효. accessToken 재발급 성공"),
			@ApiResponse(responseCode = "400", description = "header로 전달받은 refreshToken과 id로 db에서 조회한 refreshToken이 다름. 로그인 상태 해제하기. 로그인 페이지로 이동시키기."),
			@ApiResponse(responseCode = "401", description = "refreshToken도 만료되어 accessToken 재발급 실패. 로그인 상태 해제하기. 로그인 페이지로 이동시키기.")
			}
	)
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody User user, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		
		// refreshToken이 만료되지 않았다면 accessToken 재발급
		if (jwtUtil.checkToken(token)) {
			System.out.println("refreshToken은 만료되지 않았으므로 accessToken 재발급 시도");

			if (token.equals(userService.getRefreshToken(user.getId()))) {
				String accessToken = jwtUtil.createAccessToken(user.getId());
				System.out.println("accesstoken 재발급 성공");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			} else {
				System.out.println("같이 전달받은 id와 refreshToken이 저장된 record의 id가 다름");
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			System.out.println("refreshToken 도 만료됨");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/all")
	public ResponseEntity<ArrayList<User>> selectAllUser() {
		ArrayList<User> list = userService.selectAllUser();
		return ResponseEntity.ok(list);
	}
}