package com.wanted.wanted_pre_onboarding_backend.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wanted.wanted_pre_onboarding_backend.common.UUIDGenerator;
import com.wanted.wanted_pre_onboarding_backend.common.exception.BaseException;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobApplicationHistory;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.User;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.CompanyRepository;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.JobApplicationHistoryRepository;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.JobRepository;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.SkillRepository;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.UserRepository;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.request.JobCreateRequest;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.request.JobUpdateRequest;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.response.JobDetailResponse;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.response.JobListResponse;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

	@Mock
	private JobRepository jobRepository;
	@Mock
	private CompanyRepository companyRepository;
	@Mock
	private SkillRepository skillRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private JobApplicationHistoryRepository jobApplicationHistoryRepository;

	@InjectMocks
	private JobService jobService;

	private UUID companyId;
	private UUID jobId;
	private UUID userId;

	@BeforeEach
	void setUp() {
		companyId = UUIDGenerator.getInstance().generateId();
		jobId = UUIDGenerator.getInstance().generateId();
		userId = UUIDGenerator.getInstance().generateId();
	}

	@Test
	void createJob_Success() {
		// Given
		JobCreateRequest req = new JobCreateRequest(
			companyId,
			"자바 개발자 채용",
			1000,
			"자바 개발자 채용합니다.",
			List.of("Java")
		);
		Company company = Company.builder()
			.name("원티드")
			.countryName("한국")
			.locationName("지역")
			.jobs(List.of())
			.build();
		when(companyRepository.get(companyId)).thenReturn(Optional.of(company));

		// When
		jobService.createJob(req);

		// Then
		verify(jobRepository).save(any(Job.class));
		verify(skillRepository).saveJobSkillList(anyList());
	}

	@Test
	void createJob_CompanyInvalid() {
		// Given
		JobCreateRequest req = new JobCreateRequest(
			companyId,
			"자바 개발자 채용",
			1000,
			"자바 개발자 채용합니다.",
			List.of("Java")
		);
		when(companyRepository.get(companyId)).thenReturn(Optional.empty());

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.createJob(req));
	}

	@Test
	void updateJob_Success() {
		// Given
		JobUpdateRequest req = new JobUpdateRequest(
			"자바 개발자 채용",
			1000,
			"자바 개발자 채용합니다.",
			List.of("Java")
		);
		Job job = mock(Job.class);
		when(jobRepository.get(jobId)).thenReturn(Optional.of(job));

		// When
		jobService.updateJob(jobId, req);

		// Then
		verify(job).updateJob(eq("자바 개발자 채용"), eq("자바 개발자 채용합니다."), eq(1000), anyList());
	}

	@Test
	void updateJob_RequestInvalid() {
		// Given
		JobUpdateRequest req = new JobUpdateRequest(null, null, null, null);

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.updateJob(jobId, req));
	}

	@Test
	void updateJob_JobInvalid() {
		// Given
		JobUpdateRequest req = new JobUpdateRequest(
			"자바 개발자 채용",
			1000,
			"자바 개발자 채용합니다.",
			List.of("Java")
		);
		when(jobRepository.get(jobId)).thenReturn(Optional.empty());

		// When
		// THen
		assertThrows(BaseException.class, () -> jobService.updateJob(jobId, req));
	}

	@Test
	void deleteJob_Success() {
		// Given
		Job job = Job.builder()
			.position("주니어 자바 개발자")
			.description("주니어 자바 개발자 채용합니다.")
			.recruitmentBonus(10000)
			.build();
		when(jobRepository.get(jobId)).thenReturn(Optional.of(job));

		// When
		jobService.deleteJob(jobId);

		// Then
		verify(jobRepository).delete(jobId);
	}

	@Test
	void deleteJob_JobInvalid() {
		// Given
		when(jobRepository.get(jobId)).thenReturn(Optional.empty());

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.deleteJob(jobId));
	}

	@Test
	void getJobList_WithSearch_Success() {
		// Given
		String search = "Java";
		Company company = Company.builder()
			.name("원티드")
			.countryName("한국")
			.locationName("지역")
			.jobs(List.of())
			.build();
		List<Job> jobList = List.of(
			Job.builder()
				.company(company)
				.position("주니어 자바 개발자")
				.description("주니어 자바 개발자 채용합니다.")
				.recruitmentBonus(10000)
				.jobSkills(List.of())
				.build()
		);
		when(jobRepository.findBySearchKeyword(search)).thenReturn(jobList);

		// When
		JobListResponse response = jobService.getJobList(search);

		// Then
		assertNotNull(response);
		verify(jobRepository).findBySearchKeyword(search);
	}

	@Test
	void getJobList_WithoutSearch_Success() {
		// Given
		Company company = Company.builder()
			.name("원티드")
			.countryName("한국")
			.locationName("지역")
			.jobs(List.of())
			.build();
		List<Job> jobList = List.of(
			Job.builder()
				.company(company)
				.position("주니어 자바 개발자")
				.description("주니어 자바 개발자 채용합니다.")
				.recruitmentBonus(10000)
				.jobSkills(List.of())
				.build()
		);
		when(jobRepository.findAll()).thenReturn(jobList);

		// When
		JobListResponse response = jobService.getJobList(null);

		// Then
		assertNotNull(response);
		verify(jobRepository).findAll();
	}

	@Test
	void getJobDetail_Success() {
		// Given
		Company company = Company.builder()
			.name("원티드")
			.countryName("한국")
			.locationName("지역")
			.jobs(List.of(
				Job.builder()
					.position("주니어 프론트 개발자")
					.description("주니어 프론트 개발자 채용합니다.")
					.recruitmentBonus(10000)
					.jobSkills(List.of())
					.build()
			))
			.build();
		Job job = Job.builder()
			.company(company)
			.position("주니어 자바 개발자")
			.description("주니어 자바 개발자 채용합니다.")
			.recruitmentBonus(10000)
			.jobSkills(List.of())
			.build();
		when(jobRepository.get(jobId)).thenReturn(Optional.of(job));

		// When
		JobDetailResponse response = jobService.getJobDetail(jobId);

		// Then
		assertNotNull(response);
	}

	@Test
	void getJobDetail_JobInvalid() {
		// Given
		when(jobRepository.get(jobId)).thenReturn(Optional.empty());

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.getJobDetail(jobId));
	}

	@Test
	void applyJob_Success() {
		// Given
		User user = User.builder()
			.name("조혜온")
			.email("ain0103@naver.com")
			.build();
		Job job = Job.builder()
			.position("주니어 자바 개발자")
			.description("주니어 자바 개발자 채용합니다.")
			.recruitmentBonus(10000)
			.build();
		when(userRepository.get(userId)).thenReturn(Optional.of(user));
		when(jobRepository.get(jobId)).thenReturn(Optional.of(job));
		when(jobApplicationHistoryRepository.exists(userId, jobId)).thenReturn(false);

		// When
		jobService.applyJob(userId, jobId);

		// Then
		verify(jobApplicationHistoryRepository).save(any(JobApplicationHistory.class));
	}

	@Test
	void applyJob_UserInvalid() {
		// Given
		when(userRepository.get(userId)).thenReturn(Optional.empty());

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.applyJob(userId, jobId));
	}

	@Test
	void applyJob_JobInvalid() {
		// Given
		User user = User.builder()
			.name("조혜온")
			.email("ain0103@naver.com")
			.build();
		when(userRepository.get(userId)).thenReturn(Optional.of(user));
		when(jobRepository.get(jobId)).thenReturn(Optional.empty());

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.applyJob(userId, jobId));
	}

	@Test
	void applyJob_AlreadyApplied() {
		// Given
		User user = User.builder()
			.name("조혜온")
			.email("ain0103@naver.com")
			.build();
		Job job = Job.builder()
			.position("주니어 자바 개발자")
			.description("주니어 자바 개발자 채용합니다.")
			.recruitmentBonus(10000)
			.build();
		when(userRepository.get(userId)).thenReturn(Optional.of(user));
		when(jobRepository.get(jobId)).thenReturn(Optional.of(job));
		when(jobApplicationHistoryRepository.exists(userId, jobId)).thenReturn(true);

		// When
		// Then
		assertThrows(BaseException.class, () -> jobService.applyJob(userId, jobId));
	}
}
