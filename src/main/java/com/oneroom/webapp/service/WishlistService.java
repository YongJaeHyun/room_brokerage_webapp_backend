package com.oneroom.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.WishlistEntity;
import com.oneroom.webapp.persistence.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishlistService {
	@Autowired
	private WishlistRepository repository;
	
	public WishlistEntity create(final WishlistEntity entity) {
		validate(entity);
		return repository.save(entity);
	}
	
	public boolean existsByMemberIdAndBoardUuid(final String memberId, final String boardUuid) {
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.existsByMemberIdAndBoardUuid(memberId, boardUuid);
	}
	
	public WishlistEntity getByMemberIdAndBoardUuid(final String memberId, final String boardUuid){
		return repository.getByMemberIdAndBoardUuid(memberId, boardUuid);
	}
	
	public WishlistEntity update(final WishlistEntity entity) {
		return repository.save(entity);
	}
	
	public void validate(final WishlistEntity entity) {
		if (entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
		if (entity.getMemberId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}
