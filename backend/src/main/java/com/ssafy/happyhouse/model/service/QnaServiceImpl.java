package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private SqlSession sqlSession;

	//QNA 등록
	@Override
	public boolean writeQna(QnaDto qnaDto) throws Exception {
		if(qnaDto.getTitle() == null || qnaDto.getContent()==null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeQna(qnaDto)==1;
	}

	//QNA 목록 가져오기
	@Override
	public List<QnaDto> listQna() throws Exception {
		return sqlSession.getMapper(QnaMapper.class).listQna();
	}

	//QNA 세부정보 가져오기
	@Override
	public QnaDto getQna(int no) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getQna(no);
	}

	//QNA 수정
	@Override
	public boolean modifyQna(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyQna(qnaDto)==1;
	}

	//QNA 답변 등록
	@Override
	public boolean answerQna(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).answerQna(qnaDto)==1;
	}
	
	//QNA 삭제
	@Override
	public boolean deleteQna(int no) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).deleteQna(no)==1;
	}

	//QNA 작성자
	@Override
	public String getUserQna(int no) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getUserQna(no);
	}
}
