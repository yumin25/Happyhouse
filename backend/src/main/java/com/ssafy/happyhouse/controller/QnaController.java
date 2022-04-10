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

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	public static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private QnaService qnaService;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> writeQna(@RequestBody QnaDto qnaDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				qnaDto.setUsername(jwtService.getUserId());
				if(qnaService.writeQna(qnaDto)) {
					resultMap.put("SUCCESS", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} else {
					status = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			} catch (Exception e) {
				logger.error("QNA 등록 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping
	public ResponseEntity<List<QnaDto>> listQna() throws Exception {
		return new ResponseEntity<List<QnaDto>>(qnaService.listQna(),HttpStatus.OK);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<QnaDto> getQna(@PathVariable("no") int no) throws Exception {
		return new ResponseEntity<QnaDto>(qnaService.getQna(no),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> modifyQna(@RequestBody QnaDto qnaDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				System.out.println(qnaService.getUserQna(qnaDto.getNo()));
				if(!jwtService.getUserId().equals(qnaService.getUserQna(qnaDto.getNo()))) {
					logger.error("작성자가 아닙니다.");
					resultMap.put("data", "notWriter");
					status = HttpStatus.ACCEPTED;
				} else {
					if(qnaService.modifyQna(qnaDto)) {
						resultMap.put("data", SUCCESS);
						status = HttpStatus.ACCEPTED;
					} else {
						status = HttpStatus.INTERNAL_SERVER_ERROR;
					}
				}
			} catch (Exception e) {
				logger.error("QNA 업데이트 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("data", "notWriter");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping("/answer")
	public ResponseEntity<Map<String, Object>> answerQna (@RequestBody QnaDto qnaDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				if(!jwtService.getUserId().equals("admin")) {
					logger.error("잘못된 접근입니다.");
					resultMap.put("data", "notAdmin");
					status = HttpStatus.ACCEPTED;
				} else {
					if(qnaService.answerQna(qnaDto)) {
						resultMap.put("data", SUCCESS);
						status = HttpStatus.ACCEPTED;
					} else {
						status = HttpStatus.INTERNAL_SERVER_ERROR;
					}
				}
			} catch (Exception e) {
				logger.error("답변 등록 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("data", "notAdmin");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<Map<String, Object>> deleteQna(@PathVariable("no") int no, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(jwtService.isUsable(request.getHeader("access-token")));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				System.out.println(qnaService.getUserQna(no));
				if(!jwtService.getUserId().equals(qnaService.getUserQna(no))) {
					logger.error("작성자가 아닙니다.");
					resultMap.put("data", "notWriter");
					status = HttpStatus.ACCEPTED;
				} else {
					if(qnaService.deleteQna(no)) {
						resultMap.put("data", SUCCESS);
						status = HttpStatus.ACCEPTED;
					} else {
						status = HttpStatus.INTERNAL_SERVER_ERROR;
					}
				}
			} catch (Exception e) {
				logger.error("QNA 삭제 실패", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
