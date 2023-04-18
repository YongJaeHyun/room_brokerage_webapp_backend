package com.oneroom.webapp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oneroom.webapp.model.BoardEntity;


public interface BoardRepository extends JpaRepository<BoardEntity, String> {
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE board_uuid = ?1", nativeQuery = true)
	List<BoardEntity> findByUuid(String boardUuid);
	List<BoardEntity> findByMemberId(String memberId);
}
