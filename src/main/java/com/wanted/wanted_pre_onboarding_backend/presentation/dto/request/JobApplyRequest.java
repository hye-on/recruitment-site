package com.wanted.wanted_pre_onboarding_backend.presentation.dto.request;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "채용공고 지원")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplyRequest {

	@Schema(description = "유저 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "fefb37e0-4ff6-4d7f-adf4-57c97bfc411c")
	@NotNull(message = "유저 ID는 필수입니다.")
	private UUID userId;

}
