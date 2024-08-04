package com.wanted.wanted_pre_onboarding_backend.infra.repositoryImpl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.User;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.UserRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
	private final JpaUserRepository jpaUserRepository;

	@Override
	public Optional<User> get(UUID userId) {
		return jpaUserRepository.findById(userId);
	}
}
