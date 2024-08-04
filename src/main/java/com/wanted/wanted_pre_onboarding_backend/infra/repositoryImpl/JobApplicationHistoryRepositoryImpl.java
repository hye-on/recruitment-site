package com.wanted.wanted_pre_onboarding_backend.infra.repositoryImpl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobApplicationHistory;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.JobApplicationHistoryRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaJobApplicationHistoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class JobApplicationHistoryRepositoryImpl implements JobApplicationHistoryRepository {
	private final JpaJobApplicationHistoryRepository jpaJobApplicationHistoryRepository;

	@Override
	public boolean exists(UUID userId, UUID jobId) {
		return jpaJobApplicationHistoryRepository.existsByUser_IdAndJob_Id(userId, jobId);
	}

	@Override
	public void save(JobApplicationHistory jobApplicationHistory) {
		jpaJobApplicationHistoryRepository.save(jobApplicationHistory);
	}
}
