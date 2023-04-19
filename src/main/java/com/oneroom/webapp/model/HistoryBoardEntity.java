package com.oneroom.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HISTORY_BOARD", uniqueConstraints= {@UniqueConstraint(columnNames="hbUuid")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HistoryBoardEntity {
	@Id
	@GeneratedValue(generator = "system-uuid") // 자동으로 id 성성
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(nullable=false)
	private String hbUuid;
	
	private String boardUuid;
	private String title;
	private String contracted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDatetime;
}
