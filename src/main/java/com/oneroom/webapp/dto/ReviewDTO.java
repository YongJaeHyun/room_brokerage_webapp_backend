package com.oneroom.webapp.dto;

import com.oneroom.webapp.model.ReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private String receiveId;
	private String writeId;
	private String text;
	private boolean done;
	
	public ReviewDTO(final ReviewEntity entity) {
		this.receiveId = entity.getReceiveId();
		this.writeId = entity.getWriteId();
		this.text = entity.getText();
		this.done = entity.isDone();
	}

	public static ReviewEntity toEntity(final ReviewDTO dto) {
		return ReviewEntity.builder()
				.receiveId(dto.getReceiveId())
				.writeId(dto.getWriteId())
				.text(dto.getText())
				.done(dto.isDone())
				.build();
	}
}
