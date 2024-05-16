package com.ssafy.homebackend.service;

import java.util.ArrayList;

import com.ssafy.homebackend.vo.SearchIdName;
import com.ssafy.homebackend.vo.SingleAptInfo;

public interface SearchHouseService {
	ArrayList<SearchIdName> searchAreaList(String searchWord);
	ArrayList<SearchIdName> searchAptList(String searchWord);
	ArrayList<SearchIdName> searchOfiList(String searchWord);
	ArrayList<SearchIdName> searchVilList(String searchWord);
	
	ArrayList<SearchIdName> getDongAptList(String dongcode);
	ArrayList<SearchIdName> getDongOfiList(String dongcode);
	ArrayList<SearchIdName> getDongVilList(String dongcode);
}
