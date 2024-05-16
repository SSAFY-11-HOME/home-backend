package com.ssafy.homebackend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.SearchHouseMapper;
import com.ssafy.homebackend.vo.SearchIdName;

@Service
public class SearchHouseServiceImpl implements SearchHouseService {
	@Autowired
	SearchHouseMapper mapper;

	@Override
	public ArrayList<SearchIdName> searchAreaList(String searchWord) {
		ArrayList<SearchIdName> arealist = mapper.searchAreaList(searchWord);
		return arealist;
	}

	@Override
	public ArrayList<SearchIdName> searchAptList(String searchWord) {
		ArrayList<SearchIdName> aptlist = mapper.searchAptList(searchWord);
		return aptlist;
	}

	@Override
	public ArrayList<SearchIdName> searchOfiList(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SearchIdName> searchVilList(String searchWord) {
		return null;
	}
//////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<SearchIdName> getDongAptList(String dongcode) {
		ArrayList<SearchIdName> aptlist = mapper.getDongAptList(dongcode);
		return aptlist;
	}

	@Override
	public ArrayList<SearchIdName> getDongOfiList(String dongcode) {
		return null;
	}

	@Override
	public ArrayList<SearchIdName> getDongVilList(String dongcode) {
		return null;
	}
}
