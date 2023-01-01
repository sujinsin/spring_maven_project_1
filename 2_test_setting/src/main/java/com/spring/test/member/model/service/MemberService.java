package com.spring.test.member.model.service;

import com.spring.test.member.model.vo.Member;

public interface MemberService {
	int insertMember(Member m);

	int checkIdDup(String id);

	Member login(Member m);
}
