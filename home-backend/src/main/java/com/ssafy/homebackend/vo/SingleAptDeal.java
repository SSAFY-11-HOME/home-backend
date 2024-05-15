package com.ssafy.homebackend.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SingleAptDeal {
	String dealDate;
	String dealAmount;
	String area; // 제곱미터
	String pArea; // 평
	String floor;
}
