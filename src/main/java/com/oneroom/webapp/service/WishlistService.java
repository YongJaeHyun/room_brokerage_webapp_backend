package com.oneroom.webapp.service;

import java.util.List;

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
	
	public void create(final WishlistEntity entity) {
		validate(entity);
		repository.save(entity);
	}
	
	public List<WishlistEntity> retrieve(final String memberId){
		return repository.findByMemberId(memberId);
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
