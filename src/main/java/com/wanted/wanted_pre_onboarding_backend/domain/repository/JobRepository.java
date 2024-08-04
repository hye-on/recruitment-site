package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;

public interface JobRepository {
	void save(Job job);

	void delete(UUID jobId);

	Optional<Job> get(UUID jobId);

	void update(Job job);

	List<Job> findBySearchKeyword(String search);

}
