package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AnnouncementDto;
import com.ssafy.happyhouse.model.AnnouncementParameterDto;
import com.ssafy.happyhouse.model.service.AnnouncementService;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;

@RestController
@RequestMapping("/notify")
@CrossOrigin("*")
public class AnnouncementController {
	public static final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@GetMapping
	public ResponseEntity<List<AnnouncementDto>> listAnnouncement(AnnouncementParameterDto announcementParameterDto) throws Exception {
		return new ResponseEntity<List<AnnouncementDto>>(announcementService.listAnnouncement(announcementParameterDto), HttpStatus.OK); 
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<AnnouncementDto> getAnnouncement(@PathVariable("no") int no) throws Exception {
		return new ResponseEntity<AnnouncementDto>(announcementService.getAnnouncement(no), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> registerAnnouncement(@RequestBody AnnouncementDto announcementDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				if(!jwtService.getUserId().equals("admin")) {
					logger.error("잘못된 접근입니다.");
					status = HttpStatus.INTERNAL_SERVER_ERROR;
				} else {
					announcementService.writeAnnouncement(announcementDto);
					resultMap.put("announcementDto",announcementDto);
					resultMap.put("data", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error("공지사항 등록 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> updateAnnouncement(@RequestBody AnnouncementDto announcementDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				if(!jwtService.getUserId().equals("admin")) {
					logger.error("잘못된 접근입니다.");
					status = HttpStatus.INTERNAL_SERVER_ERROR;
				} else {
					announcementService.updateAnnouncement(announcementDto);
					resultMap.put("announcementDto",announcementDto);
					resultMap.put("data", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error("공지사항 등록 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<Map<String, Object>> deleteAnnouncement(@PathVariable("no") int no, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				if(!jwtService.getUserId().equals("admin")) {
					logger.error("잘못된 접근입니다.");
					status = HttpStatus.INTERNAL_SERVER_ERROR;
				} else {
					if(announcementService.deleteAnnouncement(no)) {
						resultMap.put("SUCCESS", SUCCESS);
						status = HttpStatus.ACCEPTED;
					}
					else {
						status = HttpStatus.INTERNAL_SERVER_ERROR;
					}
				}
			} catch (Exception e) {
				logger.error("공지사항 등록 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
