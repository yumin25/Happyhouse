package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUsername() == null || memberDto.getPassword() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public boolean register(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).register(memberDto)==1;
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto)==1;
	}

	@Override
	public boolean deleteMember(String username) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(username) == 1;
	}
	
	@Override
	public int findMember(String username) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).findMember(username);
	}
	
	@Override
	public String getAccessToken(String authorize_code) throws Exception {
		String access_Token = "";
		String refresh_Token = "";
		String requestUrl = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=4ff681f8072ca877106b80da365ad436");
			sb.append("&redirect_uri=http://localhost:8080/user/kakao");
			sb.append("&code="+authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line ="";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	@Override
	public MemberDto kakaoLogin(String access_Token) throws Exception {
		System.out.println(access_Token);
		MemberDto member = new MemberDto();
		String requestUrl = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			System.out.println(conn.getResponseMessage());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line ="";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String id = element.getAsJsonObject().get("id").getAsString();
			String email = null;
			
			if(kakao_account.getAsJsonObject()!=null){
				email = kakao_account.getAsJsonObject().get("email").getAsString();
				member.setUsername(id);
				member.setEmail(email);
				member.setPassword(id);
				member.setAddress("등록해 주세요");
				member.setPhone("등록해 주세요");
				member.setName("등록해 주세요");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}
