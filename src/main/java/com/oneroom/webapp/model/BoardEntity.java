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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "boardUuid") }, name = "LEASE_BOARD")
public class BoardEntity {
	@Id
	@GeneratedValue(generator = "system-uuid") // 자동으로 id 성성
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String boardUuid;
	private String memberId;
	private String title;
	@Column(name="text",columnDefinition="LONGTEXT")
	private String text;
	private String space;
	private String location;
	private String price;
	private String deposit;
	private String contracted;
	private String contractedMemberId;
}
