package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * VO(Value Object) => DTO(Data Transfer Object)
 */

/*
 * @Data: lombok 라이브러리를 사용해서 자동으로 set/get 생성
 */
@Data
public class MemberVO {

	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;

}
