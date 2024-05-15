package com.ssafy.homebackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.SingleAptDeal;

@Mapper
public interface SingleAptDealMapper {
	ArrayList<SingleAptDeal> selectSingleAptDeals(String aptCode);
}
