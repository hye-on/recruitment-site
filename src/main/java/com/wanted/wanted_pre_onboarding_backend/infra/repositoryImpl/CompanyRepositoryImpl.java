package com.wanted.wanted_pre_onboarding_backend.infra.repositoryImpl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.CompanyRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaCompanyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
	private final JpaCompanyRepository jpaCompanyRepository;

	@Override
	public boolean exists(UUID companyId) {
		return jpaCompanyRepository.existsById(companyId);
	}

	@Override
	public Optional<Company> get(UUID companyId) {
		return jpaCompanyRepository.findById(companyId);
	}
}
