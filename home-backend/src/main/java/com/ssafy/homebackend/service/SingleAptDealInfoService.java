package com.ssafy.homebackend.service;

import com.ssafy.homebackend.vo.SingleAptInfo;

public interface SingleAptDealInfoService {
	public SingleAptInfo selectSingleAptInfo(String aptCode);
}
