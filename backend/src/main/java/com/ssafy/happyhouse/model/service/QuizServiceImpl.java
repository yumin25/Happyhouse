package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QuizDto;
import com.ssafy.happyhouse.model.mapper.QuizMapper;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<QuizDto> listQuiz() throws Exception {
		return sqlSession.getMapper(QuizMapper.class).listQuiz();
	}
}
