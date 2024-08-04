package com.wanted.wanted_pre_onboarding_backend.presentation.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.wanted_pre_onboarding_backend.common.StatusCode;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.BaseResponse;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.request.JobApplyRequest;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.request.JobCreateRequest;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.request.JobUpdateRequest;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.response.JobDetailResponse;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.response.JobListResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "채용공고")
@RestController
@RequestMapping("/api/job")
public class JobController {

	@Operation(summary = "채용공고 생성")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "생성 되었습니다."),
		@ApiResponse(responseCode = "400", description = "요청 값을 확인해주세요."),
	})
	@PostMapping
	public BaseResponse<StatusCode> createJob(
		@RequestBody JobCreateRequest req
	) {
		return new BaseResponse<>(StatusCode.SUCCESS_CREATED);
	}

	@Operation(summary = "채용공고 수정")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "요청에 성공하였습니다."),
		@ApiResponse(responseCode = "404", description = "요청 값을 찾을 수 없습니다."),
	})
	@PatchMapping("/{jobId}")
	public BaseResponse<StatusCode> updateJob(
		@Parameter(description = "채용공고 id", example = "ac272eba-4122-4efe-96d0-562f59bc6ffc") @PathVariable UUID jobId,
		@RequestBody JobUpdateRequest req
	) {
		return new BaseResponse<>(StatusCode.SUCCESS);
	}

	@Operation(summary = "채용공고 삭제")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "요청에 성공하였습니다."),
		@ApiResponse(responseCode = "404", description = "요청 값을 찾을 수 없습니다."),
	})
	@DeleteMapping("/{jobId}")
	public BaseResponse<StatusCode> deleteJob(
		@Parameter(description = "채용공고 id", example = "ac272eba-4122-4efe-96d0-562f59bc6ffc") @PathVariable UUID jobId
	) {
		return new BaseResponse<>(StatusCode.SUCCESS);
	}

	@Operation(summary = "채용공고 목록 조회 및 검색")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.", content = @Content(schema = @Schema(implementation = JobListResponse.class))),
	})
	@GetMapping
	public BaseResponse<JobListResponse> getJobList(
		@Parameter @RequestParam(required = false) String search
	) {
		JobListResponse jobListResponse = JobListResponse.builder().build();
		return new BaseResponse<>(StatusCode.SUCCESS, jobListResponse);
	}

	@Operation(summary = "채용공고 상세 페이지 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.", content = @Content(schema = @Schema(implementation = JobDetailResponse.class))),
		@ApiResponse(responseCode = "404", description = "요청 값을 찾을 수 없습니다."),
	})
	@GetMapping("/{jobId}/detail")
	public BaseResponse<JobDetailResponse> getJobDetail(
		@Parameter(description = "채용공고 id", example = "ac272eba-4122-4efe-96d0-562f59bc6ffc") @PathVariable UUID jobId
	) {
		JobDetailResponse jobDetailResponse = JobDetailResponse.builder().build();
		return new BaseResponse<>(StatusCode.SUCCESS, jobDetailResponse);
	}

	@Operation(summary = "채용공고 지원")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "생성되었습니다."),
		@ApiResponse(responseCode = "400", description = "요청 값을 확인해주세요."),
	})
	@PostMapping("/{jobId}/application")
	public BaseResponse<StatusCode> applyJob(
		@Parameter(description = "채용공고 id", example = "ac272eba-4122-4efe-96d0-562f59bc6ffc") @PathVariable UUID jobId,
		@RequestBody JobApplyRequest req
	) {

		return new BaseResponse<>(StatusCode.SUCCESS_CREATED);
	}
}
