package com.ssafy.homebackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.SearchIdTypeName;

@Mapper
public interface SearchHouseMapper {
	ArrayList<SearchIdTypeName> searchAreaList(String searchWord);
	ArrayList<SearchIdTypeName> searchAptList(String searchWord);
//	ArrayList<SearchIdTypeName> searchOfiList(String searchWord);
//	ArrayList<SearchIdTypeName> searchVilList(String searchWord);
}
