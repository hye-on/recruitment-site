package com.wanted.wanted_pre_onboarding_backend.presentation.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "채용공고 수정")
@Getter
public class JobUpdateRequest {

	@Schema(description = "채용 포지션", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "안드로이드 개발자")
	private String position;

	@Schema(description = "채용 보상금", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "300000")
	private Integer recruitmentBonus;

	@Schema(description = "채용 내용", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "안드로이드 개발자를 채용합니다.")
	private String description;

	@Schema(description = "기술 리스트", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "[\"Compose\", \"kotlin\"]")
	private List<String> skillList;
}
