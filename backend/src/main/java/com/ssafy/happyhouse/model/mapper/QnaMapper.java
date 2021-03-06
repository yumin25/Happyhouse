package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QnaDto;

@Mapper
public interface QnaMapper {
	public int writeQna(QnaDto qnaDto) throws SQLException;
	public List<QnaDto> listQna() throws SQLException;
	public int deleteQna(int no) throws SQLException;
	public QnaDto getQna(int no) throws SQLException;
	public int modifyQna(QnaDto qnaDto) throws SQLException;
	public int answerQna(QnaDto qnaDto) throws SQLException;
	public String getUserQna(int no) throws SQLException;
}
