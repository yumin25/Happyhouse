package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.QnaDto;

public interface QnaService {
	public boolean writeQna(QnaDto qnaDto) throws Exception;
	public List<QnaDto> listQna() throws Exception;
	public boolean deleteQna(int no) throws Exception;
	public QnaDto getQna(int no) throws Exception;
	public boolean modifyQna(QnaDto qnaDto) throws Exception;
	public boolean answerQna(QnaDto qnaDto) throws Exception;
	public String getUserQna(int no) throws Exception;
}
