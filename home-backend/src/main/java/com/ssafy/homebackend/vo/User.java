package com.ssafy.homebackend.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String registerDate;
	private Boolean isBroker;
	private Boolean isAdmin;
}
