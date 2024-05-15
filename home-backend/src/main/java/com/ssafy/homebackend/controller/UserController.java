package com.ssafy.homebackend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.UserService;
import com.ssafy.homebackend.vo.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(name="유저 컨트롤러", description="유저 관련 요청 처리 클래스")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<ArrayList<User>> selectAllUser() {
		ArrayList<User> list = userService.selectAllUser();
		return ResponseEntity.ok(list);
	}
}