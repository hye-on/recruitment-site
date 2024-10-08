package com.wanted.wanted_pre_onboarding_backend.presentation.dto.response;

import java.util.List;
import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "채용공고 상세 정보")
@Builder
@Getter
@AllArgsConstructor
public class JobDetailResponse {

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

	@Schema(description = "채용 내용", example = "원티드 서울 Backend 주니어 개발자 채용")
	private String description;

	@Schema(description = "기술 리스트", example = "[\"Spring\", \"Java\", \"Redis\"]")
	private List<String> skillList;

	@Schema(description = "회사의 다른 채용공고 목록")
	private List<OtherJob> otherJobList;

	@Schema(description = "회사의 다른 채용공고")
	@Builder
	@Getter
	@AllArgsConstructor
	public static class OtherJob {

		@Schema(description = "채용공고 id", example = "2c0227b4-5a7b-4094-87a9-f9a36627bd84")
		private UUID jobId;

		@Schema(description = "채용 포지션", example = "Backend 주니어")
		private String position;

		@Schema(description = "채용 보상금", example = "100000")
		private Integer recruitmentBonus;

		@Schema(description = "기술 리스트", example = "[\"NestJS\", \"TypeScript\", \"Prisma\"]")
		private List<String> skillList;

		public static OtherJob from(Job job) {
			List<String> skillList = job
				.getJobSkills()
				.stream()
				.map(jobSkill -> jobSkill.getSkill().getName())
				.toList();

			return OtherJob.builder()
				.jobId(job.getId())
				.position(job.getPosition())
				.recruitmentBonus(job.getRecruitmentBonus())
				.skillList(skillList)
				.build();
		}
	}

	public static JobDetailResponse from(Job currentJob, List<Job> jobList) {
		List<OtherJob> otherJobList = jobList
			.stream()
			.map(OtherJob::from)
			.toList();

		List<String> skillList = currentJob
			.getJobSkills()
			.stream()
			.map(skill -> skill.getSkill().getName())
			.toList();

		return JobDetailResponse.builder()
			.companyName(currentJob.getCompany().getName())
			.countryName(currentJob.getCompany().getCountryName())
			.locationName(currentJob.getCompany().getLocationName())
			.position(currentJob.getPosition())
			.recruitmentBonus(currentJob.getRecruitmentBonus())
			.description(currentJob.getDescription())
			.skillList(skillList)
			.otherJobList(otherJobList)
			.build();
	}
}
