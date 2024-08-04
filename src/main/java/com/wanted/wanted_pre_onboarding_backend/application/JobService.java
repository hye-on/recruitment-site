package com.wanted.wanted_pre_onboarding_backend.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.wanted_pre_onboarding_backend.common.StatusCode;
import com.wanted.wanted_pre_onboarding_backend.common.exception.BaseException;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobApplicationHistory;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobSkill;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JobService {

	private final JobRepository jobRepository;
	private final CompanyRepository companyRepository;
	private final SkillRepository skillRepository;
	private final UserRepository userRepository;
	private final JobApplicationHistoryRepository jobApplicationHistoryRepository;

	public void createJob(JobCreateRequest req) {
		Company company = companyRepository.get(req.getCompanyId())
			.orElseThrow(() -> new BaseException(StatusCode.BAD_REQUEST));

		Job job = Job.builder()
			.company(company)
			.position(req.getPosition())
			.description(req.getDescription())
			.build();
		jobRepository.save(job);

		List<Skill> skillList = createSkillIfNotExist(req.getSkillList());
		List<JobSkill> jobSkillList = createJobSkillList(job, skillList);
		skillRepository.saveJobSkillList(jobSkillList);
	}

	public void updateJob(UUID jobId, JobUpdateRequest req) {
		Job job = jobRepository.get(jobId)
			.orElseThrow(() -> new BaseException(StatusCode.NOT_FOUND));

		List<Skill> skillList = createSkillIfNotExist(req.getSkillList());
		List<JobSkill> jobSkillList = createJobSkillList(job, skillList);

		job.updateJob(req.getPosition(), req.getDescription(), req.getRecruitmentBonus(), jobSkillList);
	}

	public void deleteJob(UUID jobId) {
		jobRepository.get(jobId)
			.orElseThrow(() -> new BaseException(StatusCode.NOT_FOUND));
		jobRepository.delete(jobId);
	}

	@Transactional(readOnly = true)
	public JobListResponse getJobList(String search) {
		List<Job> jobList;
		if (search != null && !search.trim().isEmpty()) {
			jobList = jobRepository.findBySearchKeyword(search);
		} else {
			jobList = jobRepository.findAll();
		}
		return JobListResponse.from(jobList);
	}

	@Transactional(readOnly = true)
	public JobDetailResponse getJobDetail(UUID jobId) {
		Job job = jobRepository.get(jobId)
			.orElseThrow(() -> new BaseException(StatusCode.NOT_FOUND));

		List<Job> otherJobList = job
			.getCompany()
			.getJobs()
			.stream()
			.filter(o -> o.getId() != jobId)
			.toList();

		return JobDetailResponse.from(job, otherJobList);
	}

	public void applyJob(UUID userId, UUID jobId) {
		User user = userRepository.get(userId)
			.orElseThrow(() -> new BaseException(StatusCode.BAD_REQUEST));
		Job job = jobRepository.get(jobId)
			.orElseThrow(() -> new BaseException(StatusCode.NOT_FOUND));

		if (jobApplicationHistoryRepository.exists(userId, jobId)) {
			throw new BaseException(StatusCode.BAD_REQUEST);
		}

		JobApplicationHistory jobApplicationHistory = JobApplicationHistory.builder()
			.user(user)
			.job(job)
			.build();
		jobApplicationHistoryRepository.save(jobApplicationHistory);
	}

	private List<Skill> createSkillIfNotExist(List<String> skillNameList) {
		List<Skill> skillList = new ArrayList<>();
		for (String skillName : skillNameList) {
			Skill skill = skillRepository.findByName(skillName).orElseGet(() -> {
				Skill newSkill = Skill.builder()
					.name(skillName)
					.build();
				skillRepository.save(newSkill);
				return newSkill;
			});
			skillList.add(skill);
		}
		return skillList;
	}

	private List<JobSkill> createJobSkillList(Job job, List<Skill> skillList) {
		List<JobSkill> jobSkillList = new ArrayList<>();
		for (Skill skill : skillList) {
			JobSkill jobSkill = JobSkill.builder()
				.job(job)
				.skill(skill)
				.build();
			jobSkillList.add(jobSkill);
		}
		return jobSkillList;
	}
}
