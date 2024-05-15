package com.ssafy.homebackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SingleAptInfo {
	String aptCode;
	String apartmentName;
	String base;
	String jibun;
	String road;
	ArrayList<SingleAptDeal> dealList;
	
	public SingleAptInfo(
			String aptCode, 
			String apartmentName, 
			String base, 
			String jibun, 
			String road,
			ArrayList<SingleAptDeal> dealList) {
		super();
		this.aptCode = aptCode;
		this.apartmentName = apartmentName;
		this.base = base;
		this.jibun = jibun;
		this.road = road;
		this.dealList = null;
	}
}
