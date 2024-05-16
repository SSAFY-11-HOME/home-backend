package com.ssafy.homebackend.service;

import java.util.ArrayList;

import com.ssafy.homebackend.vo.SearchIdTypeName;

public interface SearchHouseService {
	ArrayList<SearchIdTypeName> searchAreaList(String searchWord);
	ArrayList<SearchIdTypeName> searchAptList(String searchWord);
	ArrayList<SearchIdTypeName> searchOfiList(String searchWord);
	ArrayList<SearchIdTypeName> searchVilList(String searchWord);
}
