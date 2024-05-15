package com.ssafy.homebackend.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.AreaDeal;


@Mapper
public interface AreaAptDealMapper {
	ArrayList<AreaDeal> selectAptAreaDeal(Map<String, String> param);
}
