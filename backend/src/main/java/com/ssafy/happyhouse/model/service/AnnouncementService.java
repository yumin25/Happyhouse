package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AnnouncementDto;
import com.ssafy.happyhouse.model.AnnouncementParameterDto;

public interface AnnouncementService {
	void writeAnnouncement(AnnouncementDto annDto) throws Exception;
	List<AnnouncementDto> listAnnouncement(AnnouncementParameterDto announcementParameterDto) throws Exception;
	AnnouncementDto getAnnouncement(int annNo) throws Exception;
	void updateAnnouncement(AnnouncementDto annDto) throws Exception;
	public boolean deleteAnnouncement(int annNo) throws Exception;
}
