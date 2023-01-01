package com.spring.test.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.test.member.exception.MemberException;
import com.spring.test.member.model.service.MemberService;
import com.spring.test.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {

	@Autowired
	private MemberService mService;

	@Autowired 
	private BCryptPasswordEncoder bcrypt;
	

	@RequestMapping("enrollView.me")
	public String enrollView() {
		return "memberJoin";
	}

	@RequestMapping("dupId.me")
	public void duplicateId(@RequestParam("id") String id, HttpServletResponse response) {
		System.out.println(id);

		int result = mService.checkIdDup(id);

		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	@RequestMapping(value="minsert.me", method = RequestMethod.POST)
	public String insertMember(@ModelAttribute Member m, @RequestParam("post") String post, @RequestParam("address1") String address1,
			@RequestParam("address2") String address2) {
		
		System.out.println(m);
		m.setAddress(post + "/" + address1 + "/" + address2);
		
		String encPwd = bcrypt.encode(m.getPwd());
		
		m.setPwd(encPwd);
		System.out.println(m);
		int result = mService.insertMember(m);
		
		System.out.println(m);
		if(result > 0) {
			return "redirect:/";
		}else {
			throw new MemberException("회원가입에 실패하였습니다.");
		}
	}
	
	@RequestMapping("login.me")
	public String login(Member m, Model model) {
		Member loginMember = mService.login(m); 
		boolean match = bcrypt.matches(m.getPwd(), loginMember.getPwd());
		
		if(match) {
			model.addAttribute("loginUser", loginMember);
			return "redirect:/";
		}else {
			throw new MemberException("로그인에 실패하였습니다");
		}
	}
	
	@RequestMapping("myinfo.me")
	public String myinfo() {
		return "mypage";
	}

}
