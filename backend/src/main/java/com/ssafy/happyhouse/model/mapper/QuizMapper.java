package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QuizDto;

@Mapper
public interface QuizMapper {
	List<QuizDto> listQuiz() throws Exception;
}
