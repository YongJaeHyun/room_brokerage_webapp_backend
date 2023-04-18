package com.oneroom.webapp.dto;

import com.oneroom.webapp.model.WishlistEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {
	private boolean state;
	private String boardUuid;
	private String memberId;
	
	public WishlistDTO(final WishlistEntity entity) {
		this.state = entity.isState();
		this.boardUuid = entity.getBoardUuid();
	}
	
	public static WishlistEntity toEntity(final WishlistDTO dto) {
		return WishlistEntity.builder()
				.state(dto.isState())
				.boardUuid(dto.getBoardUuid())
				.build();
	}
}
