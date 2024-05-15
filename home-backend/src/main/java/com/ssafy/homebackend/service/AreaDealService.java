package com.ssafy.homebackend.service;

import java.util.ArrayList;
import java.util.Map;

import com.ssafy.homebackend.vo.AreaDeal;

public interface AreaDealService {
	public ArrayList<AreaDeal> selectAreaDeal(Map<String, String> param);
}
