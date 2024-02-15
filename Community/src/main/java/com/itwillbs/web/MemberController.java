package com.itwillbs.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

	// http://localhost:8088/member/login
	// 로그인 - GET
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void memberLoginGET() {
		logger.debug("login.jsp - memberLoginGET() 호출");
		logger.debug("/member/login.jsp 연결");
	}

	// 로그인 - POST
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memberLoginPOST(MemberVO vo, HttpSession session/* ,
																	@ModelAttribute("userid") String userid,
																	@RequestParam("userpw") String userpw */) {
		logger.debug("login.jsp - memberLoginPOST() 호출");
		logger.debug("로그인 vo: " + vo);

		MemberVO resultVO = mService.memberLogin(vo);

		String addr = "";

		if (resultVO == null) {
			logger.debug("로그인 실패!");

			addr = "/member/login";
		} else {
			logger.debug("로그인 성공");

			// 로그인 성공한 계정 정보를 세션에 저장
			session.setAttribute("id", resultVO.getUserid());

			addr = "/member/main";
		}

		return "redirect:" + addr;
	}

	// http://localhost:8088/member/main
	// main page - GET
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String memberMainGET(HttpSession session) {
		logger.debug("memberMainGET() 실행");
		logger.debug("main.jsp 이동");

		return "/member/main";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String memberLogoutGET(HttpSession session) {
		logger.debug("memberLogoutGET() 실행");

		// 세션 정보 초기화
		session.invalidate();

		logger.debug("세션 객체 초기화");

		return "redirect:/member/main";
	}
}
