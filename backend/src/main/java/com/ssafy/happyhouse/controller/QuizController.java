package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.QuizDto;
import com.ssafy.happyhouse.model.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {	
	@Autowired
	private QuizService quizService;
	
	@GetMapping
	public ResponseEntity<List<QuizDto>> getQuiz(QuizDto quizDto) throws Exception {
		return new ResponseEntity<List<QuizDto>>(quizService.listQuiz(), HttpStatus.OK);		
	}
}
