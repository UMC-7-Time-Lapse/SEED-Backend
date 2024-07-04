package com.hackathon.TimeLapse.article;

import lombok.AllArgsConstructor;

public record SimpleArticleDTO(
	Double latitude,
	Double longitude,
	Long memberId,
	String title,
	String description
) {
}
