package com.wanted.wanted_pre_onboarding_backend.infra.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.JobRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaJobRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.specification.JpaJobSpecification;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class JobRepositoryImpl implements JobRepository {
	private final JpaJobRepository jpaJobRepository;

	@Override
	public void save(Job job) {
		jpaJobRepository.save(job);
	}

	@Override
	public void delete(UUID jobId) {
		jpaJobRepository.deleteById(jobId);
	}

	@Override
	public Optional<Job> get(UUID jobId) {
		return jpaJobRepository.findById(jobId);
	}

	@Override
	public void update(Job job) {
		jpaJobRepository.save(job);
	}

	@Override
	public List<Job> findBySearchKeyword(String search) {
		Specification<Job> spec = JpaJobSpecification.searchInAllFields(search);
		return jpaJobRepository.findAll(spec);
	}

	@Override
	public List<Job> findAll() {
		return jpaJobRepository.findAll();
	}

}
