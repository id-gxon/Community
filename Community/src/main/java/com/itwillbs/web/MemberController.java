package com.itwillbs.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberService mService;

	// http://localhost:8088/member/memberjoin
	// 회원가입(입력) - GET
	@RequestMapping(value = "/memberjoin", method = RequestMethod.GET)
	public void memberJoinGET() {
		logger.debug("memberJoinGET() 실행 - 회원 정보 입력");
		logger.debug("연결된 뷰 페이지로 이동");
	}

	// 회원가입(처리) - POST
	@RequestMapping(value = "/memberjoin", method = RequestMethod.POST)
	public String MemberJoinPOST(MemberVO vo) {
		logger.debug("MemberJoinPOST() 실행 - 회원 정보 처리");

		// 0. 한글 처리 - web.xml에 필터 사용 처리

		// 1. 전달 정보 저장(submit)
		logger.debug("회원가입 vo: " + vo);

		// 2. Service -> DB에 전달 정보 저장
		mService.memberJoin(vo);

		logger.debug("회원가입 성공");

		// 3. 페이지 이동(로그인 페이지 - GET)
		return "redirect:/member/login";
	}
}
