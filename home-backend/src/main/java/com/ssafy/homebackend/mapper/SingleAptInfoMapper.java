package com.ssafy.homebackend.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homebackend.vo.SingleAptInfo;

@Mapper
public interface SingleAptInfoMapper {
	SingleAptInfo selectSingleAptInfo(String aptCode);
}
