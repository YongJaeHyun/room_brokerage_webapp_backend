package com.oneroom.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String token;
	private String memberId;
	private String pw;
	private String nickname;
	private String address;
	private String phone;
	private String review_r;
	private String review_w;
}
