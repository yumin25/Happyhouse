package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.QuizDto;

public interface QuizService {
	List<QuizDto> listQuiz() throws Exception;
}
