package com.ssafy.homebackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@Tag(name="보드 컨트롤러", description="QnA 게시판 관련 요청 처리 클래스")
public class BoardController {

}
