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

	public BoardEntity create(final BoardEntity entity) {
		validate(entity);
		return repository.save(entity);
	}
	
	public List<BoardEntity> getAllBoards() {
		return repository.findAll();
	}
	
	public List<BoardEntity> getBoardByMemberId(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.findByMemberId(memberId);
	}
	
	public BoardEntity getBoardByBoardUuid(final String boardUuid){
		return repository.findByBoardUuid(boardUuid);
	}
	
	public List<BoardEntity> getBoardContractingByContractedMemberId(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.getBoardContractingByContractedMemberId(memberId);
	}
	
	public List<BoardEntity> getBoardContractingByMemberId(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.getBoardContractingByMemberId(memberId);
	}
	
	public List<BoardEntity> getBoardContractedByContractedMemberId(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.getBoardContractedByContractedMemberId(memberId);
	}
	
	public List<BoardEntity> getBoardContractedByMemberId(final String memberId){
		if (memberId == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
		return repository.getBoardContractedByMemberId(memberId);
	}
	
	public List<BoardEntity> getBoardContractingByBoardUuid(final String boardUuid){
		return repository.getBoardContractingByBoardUuid(boardUuid);
	}
	
	public BoardEntity update(final BoardEntity entity){
		validate(entity);
		repository.save(entity);
		return repository.findByBoardUuid(entity.getBoardUuid());
	}
	
	public List<BoardEntity> findBoardsByMemberIdAndFavorite(final String memberId){
		return repository.findBoardsByMemberIdAndFavorite(memberId);
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
