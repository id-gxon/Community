package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	// 회원가입 처리
	public void memberJoin(MemberVO vo);

	// 로그인 처리
	public MemberVO memberLogin(MemberVO vo);

}
