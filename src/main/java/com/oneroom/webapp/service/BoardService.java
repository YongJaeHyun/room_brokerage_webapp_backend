package com.oneroom.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.BoardEntity;
import com.oneroom.webapp.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {
	@Autowired
	private BoardRepository repository;

	public List<BoardEntity> create(final BoardEntity entity) {
		validate(entity);
		repository.save(entity);
		return repository.findByMemberId(entity.getMemberId());
	}
	
	public List<BoardEntity> retrieve(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.findByMemberId(memberId);
	}
	
	public List<BoardEntity> update(final BoardEntity entity){
		validate(entity);
		repository.save(entity);
		return repository.findByUuid(entity.getBoardUuid());
	}
	
	public void validate(final BoardEntity entity) {
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
