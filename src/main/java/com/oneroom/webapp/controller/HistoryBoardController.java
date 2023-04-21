package com.oneroom.webapp.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.oneroom.webapp.dto.HistoryBoardDTO;
import com.oneroom.webapp.dto.ResponseDTO;
import com.oneroom.webapp.model.HistoryBoardEntity;
import com.oneroom.webapp.service.HistoryBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("history_board")
public class HistoryBoardController {
	@Autowired
	private HistoryBoardService service;
	
	@PostMapping
	public ResponseEntity<?> createHistoryBoard(@AuthenticationPrincipal String memberId, @RequestBody HistoryBoardDTO dto) {
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		
		HistoryBoardEntity entity =  HistoryBoardDTO.toEntity(dto);
		log.info("Log: dto => entity ok!");
		
		entity.setBoardUuid(dto.getBoardUuid());
		entity.setUpdatedDatetime(new Date());
		
		service.create(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(null);
	}
	
	@PutMapping
	public ResponseEntity<?> updateHistoryBoard(@AuthenticationPrincipal String memberId, @RequestBody HistoryBoardDTO dto) {
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		
		HistoryBoardEntity entity = service.findByBoardUuid(dto.getBoardUuid());
		log.info("Log: dto => entity ok!");
	
		entity.setUpdatedDatetime(new Date());
		entity.setContracted(dto.getContracted());
		
		service.update(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllHistoryBoard(@AuthenticationPrincipal String memberId) {
		if(memberId == null) {
			new RuntimeException("Unknown User");
		}
		
		List<HistoryBoardEntity> entities = service.getAllHistoryBoard();
		List<HistoryBoardDTO> dtos = entities.stream().map(HistoryBoardDTO::new).collect(Collectors.toList());
		ResponseDTO<HistoryBoardDTO> response = ResponseDTO.<HistoryBoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
}
