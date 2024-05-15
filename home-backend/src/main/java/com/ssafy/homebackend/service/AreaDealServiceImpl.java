package com.ssafy.homebackend.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homebackend.mapper.AreaAptDealMapper;
import com.ssafy.homebackend.vo.AreaDeal;

@Service
public class AreaDealServiceImpl implements AreaDealService {
	@Autowired
	AreaAptDealMapper aptMapper;
	// @Autowired
	// AreaOfiDealMapper ofiMapper;
	// @Autowired
	// AreaVilDealMapper vilMapper;

	@Override
	public ArrayList<AreaDeal> selectAreaDeal(Map<String, String> param) {
		ArrayList<AreaDeal> apt = aptMapper.selectAptAreaDeal(param);
		setHouseType(apt, "apartment");
		// ArrayList<AreaDeal> ofi = ofiMapper.selectOfiAreaDeal(param);
		// setHouseType(ofi, "officetel");
		// ArrayList<AreaDeal> vil = vilMapper.selectVilAreaDeal(param);
		// setHouseType(vil, "villa");

		ArrayList<AreaDeal> list = new ArrayList<>();
		list.addAll(apt);
		// list.addAll(ofi);
		// list.addAll(vil);
		
		return list;
	}

	private void setHouseType(ArrayList<AreaDeal> list, String houseType) {
		for (AreaDeal ad : list)
			ad.setType(houseType);
	}

}
