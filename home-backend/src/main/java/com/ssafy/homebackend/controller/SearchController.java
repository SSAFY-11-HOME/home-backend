package com.ssafy.homebackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.SearchHouseService;
import com.ssafy.homebackend.vo.AreaDeal;
import com.ssafy.homebackend.vo.SearchIdName;
import com.ssafy.homebackend.vo.SingleAptInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
@Tag(name = "검색 컨트롤러", description = "검색 요청 처리 클래스")
public class SearchController {
	@Autowired
	SearchHouseService searchHouseService;

	@Operation(summary = "지역/주택 검색", description = "검색어와 연관된 지역, 주택 리스트 반환. 리스트key는 searchArea/searchApt/searchOfi/searchVil. id는 dongCode/aptCode/ofiCode/vilCode")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상"), })
	@GetMapping("/{searchWord}")
	public ResponseEntity<HashMap<String, ArrayList<SearchIdName>>> search(
			@Parameter(description = "검색어") @PathVariable String searchWord) {
		HashMap<String, ArrayList<SearchIdName>> map = new HashMap<>();

		ArrayList<SearchIdName> searchAreaList = searchHouseService.searchAreaList(searchWord);
		map.put("searchArea", searchAreaList); // 지역 리스트

		ArrayList<SearchIdName> searchAptList = searchHouseService.searchAptList(searchWord);
		map.put("searchApt", searchAptList); // 아파트 리스트

//		ArrayList<SearchIdTypeName> searchOfiList = searchHouseService.searchOfiList(searchWord);
//		map.put("", null); // 오피스텔 리스트

//		ArrayList<SearchIdTypeName> searchVilList = searchHouseService.searchVilList(searchWord);
//		map.put("", null); // 빌라 리스트
		return ResponseEntity.ok(map);
	}

	@Operation(summary = "지역 검색", description = "동코드 일치하는 주택들 리스트 반환. 리스트key는 dongAptList/dongOfiList/dongVilList")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상"), })
	@GetMapping("/dong/{dongcode}")
	public ResponseEntity<HashMap<String, ArrayList<SearchIdName>>> selectAreaAptDeal(
			@Parameter(description = "동코드") @PathVariable String dongcode) {
		HashMap<String, ArrayList<SearchIdName>> map = new HashMap<>();
		
		ArrayList<SearchIdName> dongAptList = searchHouseService.getDongAptList(dongcode);
		map.put("dongAptList", dongAptList);
		
//		ArrayList<SearchIdName> dongOfiList = searchHouseService.getDongOfiList(dongcode);
//		map.put("dongOfiList", dongOfiList);
		
//		ArrayList<SearchIdName> dongVilList = searchHouseService.getDongVilList(dongcode);
//		map.put("dongVilList", dongVilList);
		return ResponseEntity.ok(map);
	}
}
