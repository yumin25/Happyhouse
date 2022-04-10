package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AnnouncementDto;
import com.ssafy.happyhouse.model.AnnouncementParameterDto;

@Mapper
public interface AnnouncementMapper {
	void writeAnnouncement(AnnouncementDto annDto) throws Exception;
	List<AnnouncementDto> listAnnouncement(AnnouncementParameterDto announcementParameterDto) throws Exception;
	AnnouncementDto getAnnouncement(int annNo) throws Exception;
	void updateAnnouncement(AnnouncementDto annDto) throws Exception;
	public int deleteAnnouncement(int annNo) throws Exception;
}
