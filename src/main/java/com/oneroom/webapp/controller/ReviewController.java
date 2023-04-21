package com.oneroom.webapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneroom.webapp.dto.ResponseDTO;
import com.oneroom.webapp.dto.ReviewDTO;
import com.oneroom.webapp.model.ReviewEntity;
import com.oneroom.webapp.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("review")
public class ReviewController {
	@Autowired
	private ReviewService service;
	
	@PostMapping
	public ResponseEntity<?> createReview(@AuthenticationPrincipal String memberId, @RequestBody ReviewDTO dto) {
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		ReviewEntity entity =  ReviewDTO.toEntity(dto);
		log.info("Log: dto => entity ok!");
		
		entity.setDone(false);
		entity.setWriteId(memberId);
		
		service.create(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body("");
	}
	
	@PutMapping
	public ResponseEntity<?> updateReview(@AuthenticationPrincipal String memberId, @RequestBody ReviewDTO dto) {
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		ReviewEntity entity =  ReviewDTO.toEntity(dto);
		log.info("Log: dto => entity ok!");
		
		entity.setDone(true);
		entity.setWriteId(memberId);
		
		service.update(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body("");
	}
	
	@GetMapping("/received")
	public ResponseEntity<?> getReviewByReceiveId(@AuthenticationPrincipal String memberId){
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		
		List<ReviewEntity> entities = service.findByReceiveId(memberId);
		List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());
		ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/wrote")
	public ResponseEntity<?> getReviewByWriteId(@AuthenticationPrincipal String memberId, @RequestParam String writeId){
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		
		List<ReviewEntity> entities = service.findByWriteId(writeId);
		List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());
		ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
}
