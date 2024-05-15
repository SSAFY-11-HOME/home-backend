package com.ssafy.homebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.SingleAptDealMapper;
import com.ssafy.homebackend.mapper.SingleAptInfoMapper;
import com.ssafy.homebackend.vo.SingleAptInfo;

@Service
public class SingleAptDealInfoServiceImpl implements SingleAptDealInfoService {
	@Autowired
	SingleAptInfoMapper saiMapper;
	@Autowired
	SingleAptDealMapper sadMapper;

	@Override
	public SingleAptInfo selectSingleAptInfo(String aptCode) {
		SingleAptInfo sai = saiMapper.selectSingleAptInfo(aptCode);
		sai.setDealList(sadMapper.selectSingleAptDeals(aptCode));
		return sai;
	}
}
