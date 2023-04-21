package com.oneroom.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.ReviewEntity;
import com.oneroom.webapp.persistence.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository repository;
	
	public void create(final ReviewEntity entity) {
		repository.save(entity);
	}
	
	public List<ReviewEntity> findByReceiveId(final String memberId) {
		return repository.findByReceiveId(memberId);
	}
	
	public List<ReviewEntity> findByWriteId(final String memberId) {
		return repository.findByWriteId(memberId);
	}
	
	public void update(final ReviewEntity entity) {
		repository.save(entity);
	}
}
