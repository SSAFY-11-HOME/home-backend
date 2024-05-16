package com.ssafy.homebackend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.SearchHouseMapper;
import com.ssafy.homebackend.vo.SearchIdTypeName;

@Service
public class SearchHouseServiceImpl implements SearchHouseService {
	@Autowired
	SearchHouseMapper mapper;

	@Override
	public ArrayList<SearchIdTypeName> searchAreaList(String searchWord) {
//		ArrayList<SearchIdTypeName> arealist = mapper.searchAreaList(searchWord);
//		for (SearchIdTypeName rec : arealist) {
//			;
//		}
		return null;
	}

	@Override
	public ArrayList<SearchIdTypeName> searchAptList(String searchWord) {
		ArrayList<SearchIdTypeName> aptlist = mapper.searchAptList(searchWord);
		for (SearchIdTypeName rec : aptlist) {
			rec.setType("apartment");
		}
		return aptlist;
	}

	@Override
	public ArrayList<SearchIdTypeName> searchOfiList(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SearchIdTypeName> searchVilList(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
