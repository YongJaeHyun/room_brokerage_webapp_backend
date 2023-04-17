package com.oneroom.webapp.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oneroom.webapp.model.MemberEntity;


public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	MemberEntity getByMemberId(String memberId);
	MemberEntity findByMemberIdAndPw(String memberId, String pw);
	boolean existsByNickname(String nickname);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MemberEntity m set nickname = ?1, pw = ?2, address = ?3, phone = ?4 where memberId = ?5")
	void updateUserInfo(String nickname, String pw, String address, String phone, String memberId);
}
