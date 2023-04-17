package com.oneroom.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints= {@UniqueConstraint(columnNames="uuid")}, name= "MEMBER")
public class MemberEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(nullable=false)
	private String uuid;
	
	@Column(nullable=false)
	private String memberId;
	
	@Column(nullable=false)
	private String pw;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEASE_BOARD")
	private BoardEntity leaseBoardByMember;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WISH_LIST") 
	private WishlistEntity wishlistByMember;
	
	private String nickname;
	private String address;
	private String phone;
	private String review_r;
	private String review_w;
}
