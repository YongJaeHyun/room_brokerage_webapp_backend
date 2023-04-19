package com.oneroom.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.HistoryBoardEntity;
import com.oneroom.webapp.persistence.HistoryBoardRepository;

@Service
public class HistoryBoardService {
	@Autowired
	private HistoryBoardRepository repository;

	public void create(final HistoryBoardEntity entity) {
		repository.save(entity);
	}
	
	public List<HistoryBoardEntity> findByBoardUuid(final String boardUuid){ 
		return repository.findByBoardUuid(boardUuid);
	}
}
