package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AnnouncementDto;
import com.ssafy.happyhouse.model.AnnouncementParameterDto;
import com.ssafy.happyhouse.model.mapper.AnnouncementMapper;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void writeAnnouncement(AnnouncementDto annDto) throws Exception {
		sqlSession.getMapper(AnnouncementMapper.class).writeAnnouncement(annDto);
	}

	@Override
	public List<AnnouncementDto> listAnnouncement(AnnouncementParameterDto announcementParameterDto) throws Exception {
		return sqlSession.getMapper(AnnouncementMapper.class).listAnnouncement(announcementParameterDto);
	}

	@Override
	public AnnouncementDto getAnnouncement(int annNo) throws Exception {
		return sqlSession.getMapper(AnnouncementMapper.class).getAnnouncement(annNo);
	}

	@Override
	public void updateAnnouncement(AnnouncementDto annDto) throws Exception {
		sqlSession.getMapper(AnnouncementMapper.class).updateAnnouncement(annDto);
	}

	@Override
	public boolean deleteAnnouncement(int annNo) throws Exception {
		return sqlSession.getMapper(AnnouncementMapper.class).deleteAnnouncement(annNo)==1;
	}
}
