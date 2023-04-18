package com.oneroom.webapp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneroom.webapp.model.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {
	List<ReviewEntity> findByReceiveId(String memberId);
	List<ReviewEntity> findByWriteId(String memberId);
}
