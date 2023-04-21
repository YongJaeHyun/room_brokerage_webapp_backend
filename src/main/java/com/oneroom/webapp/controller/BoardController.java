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
	
	@PostMapping("/create")
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String memberId, @RequestBody BoardDTO dto) {
		try {
			BoardEntity entity =  BoardDTO.toEntity(dto);
			log.info("Log: dto => entity ok!");

			// entity userId를 지정한다.
			entity.setMemberId(memberId);
			entity.setContracted("대기");

			// service.create를 통해 repository에 entity를 저장한다.
			// 이 때 넘어오는 값이 없을 수도 있으므로 List가 아닌 Optional로 한다.
			BoardEntity newEntity = service.create(entity);
			log.info("Log: service.create ok!");
			
			return ResponseEntity.ok().body(newEntity);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllBoards() {
		List<BoardEntity> entities = service.getAllBoards();
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/boardUuid")
	public ResponseEntity<?> getBoardByBoardUuid(@RequestParam String boardUuid) {
		BoardEntity entity = service.getBoardByBoardUuid(boardUuid);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getBoardByMemberId(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.getBoardByMemberId(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/contractedMember+contracting")
	public ResponseEntity<?> getBoardContractingByContractedMemberId(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.getBoardContractingByContractedMemberId(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/member+contracting")
	public ResponseEntity<?> getBoardContractingByMemberId(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.getBoardContractingByMemberId(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/contractedMember+contracted")
	public ResponseEntity<?> getBoardContractedByContractedMemberId(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.getBoardContractedByContractedMemberId(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/member+contracted")
	public ResponseEntity<?> getBoardContractedByMemberId(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.getBoardContractedByMemberId(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/boardUuid+contracting")
	public ResponseEntity<?> getBoardContractingByBoardUuid(@AuthenticationPrincipal String memberId, @RequestParam String boardUuid) {
		List<BoardEntity> entities = service.getBoardContractingByBoardUuid(boardUuid);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	
	
	@GetMapping("/favorite")
	public ResponseEntity<?> findBoardsByMemberIdAndFavorite(@AuthenticationPrincipal String memberId) {
		List<BoardEntity> entities = service.findBoardsByMemberIdAndFavorite(memberId);
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/lessee")
	public ResponseEntity<?> updateBoardByLessee(@AuthenticationPrincipal String memberId, @RequestBody BoardDTO dto) {
		try {
			BoardEntity entity =  BoardDTO.toEntity(dto);
			log.info("Log: dto => entity ok!");

			// entity userId를 지정한다.
			entity.setMemberId(dto.getMemberId());
			entity.setBoardUuid(dto.getBoardUuid());
			entity.setContractedMemberId(memberId);

			BoardEntity newEntity = service.update(entity);
			
			// entities를 dtos로 스트림 변환한다.
			return ResponseEntity.ok().body(newEntity);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/landlord")
	public ResponseEntity<?> updateBoardByLandlord(@AuthenticationPrincipal String memberId, @RequestBody BoardDTO dto) {
		try {
			BoardEntity entity = service.getBoardByBoardUuid(dto.getBoardUuid());
			entity.setContracted(dto.getContracted());

			BoardEntity newEntity = service.update(entity);
			
			// entities를 dtos로 스트림 변환한다.
			return ResponseEntity.ok().body(newEntity);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}