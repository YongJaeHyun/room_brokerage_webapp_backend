package com.oneroom.webapp.dto;

import java.util.Date;

import com.oneroom.webapp.model.HistoryBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryBoardDTO {
	private String title;
	private String contracted;
	private Date updatedDatetime;
	private String boardUuid;
	
	public HistoryBoardDTO(final HistoryBoardEntity entity) {
		this.title = entity.getTitle();
		this.contracted = entity.getContracted();
		this.boardUuid = entity.getBoardUuid();
		this.updatedDatetime = new Date();
	}

	public static HistoryBoardEntity toEntity(final HistoryBoardDTO dto) {
		return HistoryBoardEntity.builder()
				.title(dto.getTitle())
				.contracted(dto.getContracted())
				.updatedDatetime(dto.getUpdatedDatetime())
				.build();
	}
}
