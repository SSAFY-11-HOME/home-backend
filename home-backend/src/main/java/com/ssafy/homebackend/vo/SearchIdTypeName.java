package com.ssafy.homebackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchIdTypeName {
	String id; // aptCode, ofiCode, vilCode
	String type; // building type : apartment, officetel, villa
	String name; // apartmentName, officetelName, villaName
	
	public SearchIdTypeName(String id, String type, String name) {
		this.id = id;
		this.type = null;
		this.name = name;
	}
}

