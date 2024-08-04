package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.UUID;

public interface JobApplicationHistoryRepository {
	boolean exists(UUID userId, UUID jobId);
}
