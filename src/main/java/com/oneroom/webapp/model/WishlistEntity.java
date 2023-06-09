package com.oneroom.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WISH_LIST", uniqueConstraints= {@UniqueConstraint(columnNames="wishlistUuid")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WishlistEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(nullable=false)
	private String wishlistUuid;
	private String memberId;
	private String boardUuid;
	private boolean state;
}
