package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean register(MemberDto memberDto) throws Exception;
	public boolean modifyMember(MemberDto memberDto) throws Exception;
	public boolean deleteMember(String username) throws Exception;
	public String getAccessToken(String authorize_code) throws Exception;
	public MemberDto kakaoLogin(String access_Token) throws Exception;
	public int findMember(String username) throws Exception;
}