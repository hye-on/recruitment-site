package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobApplicationHistory;

public interface JobApplicationHistoryRepository {
	boolean exists(UUID userId, UUID jobId);

	void save(JobApplicationHistory jobApplicationHistory);
}
