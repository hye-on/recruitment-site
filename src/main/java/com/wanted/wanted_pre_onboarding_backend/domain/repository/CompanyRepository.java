package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;

public interface CompanyRepository {

	boolean exists(UUID companyId);

	Optional<Company> get(UUID companyId);

}
