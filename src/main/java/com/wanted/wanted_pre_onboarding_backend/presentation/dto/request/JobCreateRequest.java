package com.wanted.wanted_pre_onboarding_backend.presentation.dto.request;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(description = "채용공고 생성")
@Getter
public class JobCreateRequest {

	@Schema(description = "회사 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2c0227b4-5a7b-4094-87a9-f9a36627bd84")
	@NotNull(message = "회사 ID는 필수입니다.")
	private UUID companyId;

	@Schema(description = "채용 포지션", requiredMode = Schema.RequiredMode.REQUIRED, example = "백엔드")
	@NotNull(message = "채용 포지션은 필수입니다.")
	@NotBlank(message = "채용공고 포지션은 공백일 수 없습니다.")
	private String position;

	@Schema(description = "채용 보상금", requiredMode = Schema.RequiredMode.REQUIRED, example = "100000")
	@NotNull(message = "채용 보상금은 필수입니다.")
	@PositiveOrZero(message = "채용 보상금은 0 이상이어야 합니다.")
	private Integer recruitmentBonus;

	@Schema(description = "채용 내용", requiredMode = Schema.RequiredMode.REQUIRED, example = "주니어 개발자를 모집합니다.")
	@NotNull(message = "채용 내용은 필수입니다.")
	@NotBlank(message = "채용 내용은 공백일 수 없습니다.")
	private String description;

	@Schema(description = "기술 리스트", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"Spring\", \"Java\", \"Redis\"]")
	@NotNull(message = "사용 기술은 필수입니다.")
	@Size(min = 1, message = "최소 하나 이상의 사용 기술을 입력해야 합니다.")
	private List<String> skillList;
}
