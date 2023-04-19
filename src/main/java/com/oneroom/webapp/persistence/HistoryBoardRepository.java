package com.oneroom.webapp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneroom.webapp.model.HistoryBoardEntity;

public interface HistoryBoardRepository extends JpaRepository<HistoryBoardEntity, String> {
	List<HistoryBoardEntity> findByBoardUuid(String boardUuid);
}
