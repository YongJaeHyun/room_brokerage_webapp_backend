package com.oneroom.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneroom.webapp.dto.ResponseDTO;
import com.oneroom.webapp.dto.MemberDTO;
import com.oneroom.webapp.model.MemberEntity;
import com.oneroom.webapp.security.TokenProvider;
import com.oneroom.webapp.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class MemberController {
	@Autowired
	private MemberService service;

	@Autowired
	private TokenProvider tokenProvider;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody MemberDTO userDTO) {
		try {
			MemberEntity user = MemberEntity.builder().memberId(userDTO.getMemberId())
					.pw(passwordEncoder.encode(userDTO.getPw())).build();
			if(service.getByMemberId(userDTO.getMemberId()) == null) {
				MemberEntity registeredUser = service.create(user);
				MemberDTO responseUserDTO = MemberDTO.builder().memberId(registeredUser.getMemberId())
						.nickname(registeredUser.getNickname()).build();
				return ResponseEntity.ok().body(responseUserDTO);
			}
			else {
				ResponseDTO<?> responseDTO = ResponseDTO.builder().error("이미 존재하는 아이디입니다.").build();
				return ResponseEntity.badRequest().body(responseDTO);
			}
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error(error).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody MemberDTO memberDTO) {
		MemberEntity member = service.getByCredentials(memberDTO.getMemberId(), memberDTO.getPw(), passwordEncoder);

		if (member != null) {
			final String token = tokenProvider.create(member);
			final MemberDTO responseUserDTO = MemberDTO.builder().memberId(member.getMemberId()).token(token)
					.build(); 
			return ResponseEntity.ok().body(responseUserDTO);
		} else {
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Login failed").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal String memberId) {
		MemberEntity user = service.getByMemberId(memberId);
		log.info("user: " + user);
		if (user != null) {
			final MemberDTO responseUserDTO = MemberDTO.builder()
					.nickname(user.getNickname())
					.address(user.getAddress())
					.phone(user.getPhone())
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		} else {
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error("user not found").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal String memberId, @RequestBody MemberDTO dto) {
		try {
			MemberEntity entity = service.getByMemberId(memberId);
			entity.setNickname(dto.getNickname());
			entity.setAddress(dto.getAddress());
			entity.setPhone(dto.getPhone());
					
			service.update(entity);
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error(error).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@AuthenticationPrincipal String memberId) {
		try {	
			service.delete(memberId);
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error(error).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
}
