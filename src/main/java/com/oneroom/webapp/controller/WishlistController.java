package com.oneroom.webapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneroom.webapp.dto.ResponseDTO;
import com.oneroom.webapp.dto.WishlistDTO;
import com.oneroom.webapp.model.WishlistEntity;
import com.oneroom.webapp.service.WishlistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("wishlist")
public class WishlistController {
	@Autowired
	private WishlistService service;
	
	@PostMapping
	public ResponseEntity<?> saveWishlist(@AuthenticationPrincipal String memberId, @RequestBody WishlistDTO dto) {
		WishlistEntity entity =  WishlistDTO.toEntity(dto);
		log.info("Log: dto => entity ok!");
		
		entity.setMemberId(memberId);
		
		service.create(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveBoard(@AuthenticationPrincipal String memberId) {
		List<WishlistEntity> entities = service.retrieve(memberId);
		List<WishlistDTO> dtos = entities.stream().map(WishlistDTO::new).collect(Collectors.toList());
		ResponseDTO<WishlistDTO> response = ResponseDTO.<WishlistDTO>builder().data(dtos).build();
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(response);
	}
}
