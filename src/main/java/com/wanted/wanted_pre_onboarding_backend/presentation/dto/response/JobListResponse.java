package com.wanted.wanted_pre_onboarding_backend.presentation.dto.response;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "채용공고 목록 조회 결과")
@Builder
@Getter
public class JobListResponse {

	@Schema(description = "채용공고 목록 조회 결과")
	private List<JobItem> jobList;

	@Builder
	@Getter
	public static class JobItem {

		@Schema(description = "채용공고 id", example = "설여대 기상방")
		private UUID jobId;

		@Schema(description = "회사명", example = "원티드")
		private String companyName;

		@Schema(description = "국가명", example = "한국")
		private String countryName;

		@Schema(description = "지역명", example = "서울")
		private String locationName;

		@Schema(description = "채용 포지션", example = "Backend 주니어")
		private String position;

		@Schema(description = "채용 보상금", example = "100000")
		private Integer recruitmentBonus;

		@Schema(description = "기술 리스트", example = "[\"Spring\", \"Java\", \"Redis\"]")
		private List<String> skillList;
	}

}
