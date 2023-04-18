package com.oneroom.webapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REVIEW", uniqueConstraints= {@UniqueConstraint(columnNames="reviewUuid")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(nullable=false)
	private String reviewUuid;
	
	@OneToMany(mappedBy = "reviewByMember")
	private List<MemberEntity> members;
	
	private String receiveId;
	private String writeId;
	private String text;
}
