package com.ssafy.homebackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.SearchIdName;

@Mapper
public interface SearchHouseMapper {
	ArrayList<SearchIdName> searchAreaList(String searchWord);
	ArrayList<SearchIdName> searchAptList(String searchWord);
//	ArrayList<SearchIdTypeName> searchOfiList(String searchWord);
//	ArrayList<SearchIdTypeName> searchVilList(String searchWord);
	
	ArrayList<SearchIdName> getDongAptList(String dongcode);
//	ArrayList<SearchIdName> getDongOfiList(String dongcode);
//	ArrayList<SearchIdName> getDongVilList(String dongcode);
}
