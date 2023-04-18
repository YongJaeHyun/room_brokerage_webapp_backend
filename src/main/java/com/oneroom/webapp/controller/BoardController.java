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
import org.springframework.web.bind.annotation.RestController;

import com.oneroom.webapp.dto.ResponseDTO;
import com.oneroom.webapp.dto.BoardDTO;
import com.oneroom.webapp.model.BoardEntity;
import com.oneroom.webapp.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@PostMapping
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String memberId, @RequestBody BoardDTO dto) {
		try {
			BoardEntity entity =  BoardDTO.toEntity(dto);
			log.info("Log: dto => entity ok!");

			// entity userId를 지정한다.
			entity.setMemberId(memberId);

			// service.create를 통해 repository에 entity를 저장한다.
			// 이 때 넘어오는 값이 없을 수도 있으므로 List가 아닌 Optional로 한다.
			List<BoardEntity> entities = service.create(entity);
			log.info("Log: service.create ok!");

			// entities를 dtos로 스트림 변환한다.
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveBoard(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.retrieve(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String memberId, @RequestBody BoardDTO dto) {
		try {
			BoardEntity entity =  BoardDTO.toEntity(dto);
			log.info("Log: dto => entity ok!");

			// entity userId를 지정한다.
			entity.setMemberId(memberId);
			entity.setBoardUuid(dto.getBoardUuid());

			List<BoardEntity> entities = service.update(entity);
			
			// entities를 dtos로 스트림 변환한다.
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}