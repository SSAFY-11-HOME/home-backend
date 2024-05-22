package com.ssafy.homebackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.AreaDealService;
import com.ssafy.homebackend.service.SingleAptDealInfoService;
import com.ssafy.homebackend.vo.AreaDeal;
import com.ssafy.homebackend.vo.SingleAptInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
@Tag(name = "하우스 컨트롤러", description = "주택 관련 요청 처리 클래스")
public class HouseController {
	@Autowired
	AreaDealService areaDealService;
	@Autowired
	SingleAptDealInfoService singleAptDealInfoService;

	@Operation(summary = "주변 주택 정보 + 가장 최근 거래 내역", description = "좌표 기준 주변 주택들 + 최근 거래 정보 반환.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상"), })
	@GetMapping("/area")
	public ResponseEntity<ArrayList<AreaDeal>> selectAreaAptDeal(
			@Parameter(required = true, description = "위도1") @RequestParam(required = true, defaultValue = "33.44843745687413") String lat1,
			@Parameter(required = true, description = "위도2") @RequestParam(required = true, defaultValue = "33.452964008206735") String lat2,
			@Parameter(required = true, description = "경도1") @RequestParam(required = true, defaultValue = "126.56798357402302") String lng1,
			@Parameter(required = true, description = "경도2") @RequestParam(required = true, defaultValue = "126.57333898904454") String lng2) {
		Map<String, String> param = new HashMap<>();
		param.put("lat1", lat1);
		param.put("lng1", lng1);
		param.put("lat2", lat2);
		param.put("lng2", lng2);
		ArrayList<AreaDeal> list = areaDealService.selectAreaDeal(param);
		return ResponseEntity.ok(list);
	}

	@Operation(summary = "해당 주택 거래 내역 상세 정보", description = "해당 주택의 거래 정보 리스트 반환.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상"), })
	@GetMapping("/apt")
	public ResponseEntity<SingleAptInfo> selectSingleAptInfo(
			@Parameter(required = true, description = "아파트 식별 코드") @RequestParam(required = true, defaultValue = "11680000000534") String id) {
		SingleAptInfo sai = singleAptDealInfoService.selectSingleAptInfo(id);
		return ResponseEntity.ok(sai);
	}
}
