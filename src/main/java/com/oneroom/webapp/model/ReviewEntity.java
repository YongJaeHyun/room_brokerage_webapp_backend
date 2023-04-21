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
	
	private String receiveId;
	private String writeId;
	private String text;
	private boolean done;
}
