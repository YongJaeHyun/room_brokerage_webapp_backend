package com.oneroom.webapp.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.oneroom.webapp.model.MemberEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
	private static final String SECRET_KEY = "NMA8JPctFuna59f5";
	
	public String create(MemberEntity userEntity) {
		Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		return Jwts.builder()
				   .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				   .setSubject(userEntity.getMemberId())
				   .setIssuer("todo app")
				   .setIssuedAt(new Date())
				   .setExpiration(expireDate)
				   .compact();
	}
	
	public String validateAndGetId(String token) {
		Claims claims = Jwts.parser()
						    .setSigningKey(SECRET_KEY)
						    .parseClaimsJws(token)
						    .getBody();
		
		return claims.getSubject();
	}
}
