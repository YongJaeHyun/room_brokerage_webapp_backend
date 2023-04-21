package com.oneroom.webapp.controller;

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
		
		WishlistEntity wishlist = service.create(entity);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(wishlist);
	}
	
	@GetMapping("/exist/memberId+boardUuid")
	public ResponseEntity<?> existsByMemberIdAndBoardUuid(@AuthenticationPrincipal String memberId, @RequestParam String boardUuid) {
		boolean isExist = service.existsByMemberIdAndBoardUuid(memberId, boardUuid);
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(isExist);
	}
	
	@GetMapping("/memberId+boardUuid")
	public ResponseEntity<?> getByMemberIdAndBoardUuid(@AuthenticationPrincipal String memberId, @RequestParam String boardUuid) {
		WishlistEntity entity = service.getByMemberIdAndBoardUuid(memberId, boardUuid);
		log.info(entity.toString());
		// HTTP Status 200 상태로 response 를 전송한다.
		return ResponseEntity.ok().body(entity);
	}
	
	@PutMapping
	public ResponseEntity<?> updateWishlist(@AuthenticationPrincipal String memberId, @RequestBody WishlistDTO dto){
		WishlistEntity entity = service.getByMemberIdAndBoardUuid(memberId, dto.getBoardUuid());
		
		entity.setState(dto.isState());
		WishlistEntity wishlist = service.update(entity);
		return ResponseEntity.ok().body(wishlist);
	}
}
