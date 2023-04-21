package com.oneroom.webapp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oneroom.webapp.model.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE board_uuid = ?1", nativeQuery = true)
	BoardEntity findByBoardUuid(String boardUuid);

	List<BoardEntity> findByMemberId(String memberId);

	@Query(value = "SELECT * FROM LEASE_BOARD WHERE contracted_member_id = ?1 AND contracted = '진행중'", nativeQuery = true)
	List<BoardEntity> getBoardContractingByContractedMemberId(String memberId);
	
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE member_id = ?1 AND contracted = '대기' OR contracted = '진행중'", nativeQuery = true)
	List<BoardEntity> getBoardContractingByMemberId(String memberId);
	
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE board_uuid = ?1 AND contracted = '진행중'", nativeQuery = true)
	List<BoardEntity> getBoardContractingByBoardUuid(String boardUuid);
	
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE contracted_member_id = ?1 AND contracted = '완료'", nativeQuery = true)
	List<BoardEntity> getBoardContractedByContractedMemberId(String memberId);
	
	@Query(value = "SELECT * FROM LEASE_BOARD WHERE member_id = ?1 AND contracted = '완료'", nativeQuery = true)
	List<BoardEntity> getBoardContractedByMemberId(String memberId);
	

	@Query(value = "SELECT * FROM lease_board b JOIN wish_list w ON b.board_uuid = w.board_uuid WHERE w.member_id = ?1 AND w.state = 1", nativeQuery = true)
	List<BoardEntity> findBoardsByMemberIdAndFavorite(String memberId);
}
