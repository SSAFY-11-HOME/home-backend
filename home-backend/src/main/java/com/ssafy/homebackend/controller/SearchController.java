package com.ssafy.homebackend.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homebackend.service.SearchHouseService;
import com.ssafy.homebackend.vo.SearchIdTypeName;

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
	
	@Operation(summary = "검색어와 연관된 지역, 주택 리스트 반환")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상"), })
	@GetMapping("/{searchWord}")
	public ResponseEntity<HashMap<String, ArrayList<?>>> search(
			@Parameter(description = "검색어") @PathVariable String searchWord) {		
		HashMap<String, ArrayList<?>> map = new HashMap<>();
		
//		ArrayList<?> searchAreaList = searchHouseService.searchAreaList(searchWord);
//		map.put("searchArea", searchAreaList); // 지역 리스트
		
		ArrayList<SearchIdTypeName> searchAptList = searchHouseService.searchAptList(searchWord);
		map.put("searchApt", searchAptList); // 아파트 리스트
		
//		ArrayList<SearchIdTypeName> searchOfiList = searchHouseService.searchOfiList(searchWord);
//		map.put("", null); // 오피스텔 리스트
		
//		ArrayList<SearchIdTypeName> searchVilList = searchHouseService.searchVilList(searchWord);
//		map.put("", null); // 빌라 리스트
		return ResponseEntity.ok(map);
	}
}
