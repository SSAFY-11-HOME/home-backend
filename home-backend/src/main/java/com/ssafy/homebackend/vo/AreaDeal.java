package com.ssafy.homebackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AreaDeal {
	String id; // aptCode, ofiCode, vilCode
	String type; // building type : apartment, officetel, villa
	String name; // apartmentName, officetelName, villaName
	String floor; // 층
	String area; // 제곱미터 면적
	String pArea; // 평 면적
	String dealAmount;
	String dealDate;
	String lat;
	String lng;
	public AreaDeal(
			String id, 
			String type, 
			String name, 
			String floor, 
			String area, 
			String pArea, 
			String dealAmount,
			String dealDate, 
			String lat, 
			String lng) {
		super();
		this.id = id;
		this.type = null;
		this.name = name;
		this.floor = floor;
		this.area = area;
		this.pArea = pArea;
		this.dealAmount = dealAmount;
		this.dealDate = dealDate;
		this.lat = lat;
		this.lng = lng;
	}
}
