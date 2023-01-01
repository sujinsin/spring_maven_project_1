package com.spring.test.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.member.model.dao.MemberDAO;
import com.spring.test.member.model.vo.Member;

@Service("mService")
public class MemberServiceImpl  implements MemberService {

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertMember(Member m) {
		return mDAO.insertMember(sqlSession, m);
	}

	@Override
	public int checkIdDup(String id) {
		return mDAO.checkIdDup(sqlSession, id);
	}

	@Override
	public Member login(Member m) {
		return mDAO.login(sqlSession, m);
	}
	
}
