package com.oneroom.webapp.dto;

import com.oneroom.webapp.model.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private String boardUuid;
	private String title;
	private String text;
	private String space;
	private String location;
	private String price;
	private String deposit;
	private String contracted;

	public BoardDTO(final BoardEntity entity) {
		this.boardUuid = entity.getBoardUuid();
		this.title = entity.getTitle();
		this.text = entity.getText();
		this.space = entity.getSpace();
		this.location = entity.getLocation();
		this.price = entity.getPrice();
		this.deposit = entity.getDeposit();
		this.contracted = entity.getContracted();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.title(dto.getTitle())
				.text(dto.getText())
				.space(dto.getSpace())
				.location(dto.getLocation())
				.price(dto.getPrice())
				.deposit(dto.getDeposit())
				.contracted(dto.getContracted()).build();
	}
}
