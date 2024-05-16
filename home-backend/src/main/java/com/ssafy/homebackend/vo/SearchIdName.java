package com.ssafy.homebackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchIdName {
	String id; // dongCode, aptCode, ofiCode, vilCode
	String name; // dongName, apartmentName, officetelName, villaName
	
	public SearchIdName(String id, String name) {
		this.id = id;
		this.name = name;
	}
}

