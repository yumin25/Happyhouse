package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String username) throws SQLException;
	public int register(MemberDto memberDto) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String username) throws SQLException;
	public int findMember(String username) throws SQLException;
}
