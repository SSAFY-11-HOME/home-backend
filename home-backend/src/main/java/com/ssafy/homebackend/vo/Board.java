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
public class Board {
	private String articleId;
	private String id;
	private String date;
	private String title;
	private String contents;
	private int count;
}