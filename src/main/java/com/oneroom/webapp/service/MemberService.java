package com.oneroom.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.MemberEntity;
import com.oneroom.webapp.persistence.MemberRepository;
import com.oneroom.webapp.util.RandomNicknameGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberEntity create(final MemberEntity memberEntity) {
		if(memberEntity == null || memberEntity.getMemberId() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String memberId = memberEntity.getMemberId();
		if(memberRepository.existsById(memberId)) {
			log.warn("ID already exists {}", memberId);
			throw new RuntimeException();
		}
		
		final String nickname = RandomNicknameGenerator.generate();
		memberEntity.setNickname(nickname);
		
		return memberRepository.save(memberEntity);
	}
	
	public void update(final MemberEntity entity) {
		memberRepository.save(entity);
	}
	
	public void delete(final String memberId) {
		MemberEntity member = memberRepository.getByMemberId(memberId);
		memberRepository.delete(member);
	}
	
	public MemberEntity getByMemberId(final String memberId) {
		return memberRepository.getByMemberId(memberId);
	}
	
	
	public MemberEntity getByCredentials(final String memberId, final String pw, final PasswordEncoder encoder) {
		
		final MemberEntity originalUser = memberRepository.getByMemberId(memberId);
		log.info(originalUser.toString());
		if(originalUser != null && encoder.matches(pw, originalUser.getPw())) {
			return originalUser;
		}
		return null;
	}
}
