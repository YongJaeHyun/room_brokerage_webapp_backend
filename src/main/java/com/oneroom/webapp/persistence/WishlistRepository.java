package com.oneroom.webapp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneroom.webapp.model.WishlistEntity;

public interface WishlistRepository extends JpaRepository<WishlistEntity, String> {
	List<WishlistEntity> findByMemberId(String memberId);
	boolean existsByMemberIdAndBoardUuid(String memberId, String BoardUuid);
	WishlistEntity getByMemberIdAndBoardUuid(String memberId, String boardUuid);
}
