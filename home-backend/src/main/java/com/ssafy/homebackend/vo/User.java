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
	String id;
	String pw;
	String name;
	String email;
	String registerDate;
	Boolean isBroker;
	Boolean isAdmin;
}
