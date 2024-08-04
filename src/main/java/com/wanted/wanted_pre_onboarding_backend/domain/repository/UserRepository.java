package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.User;

public interface UserRepository {

	Optional<User> get(UUID userId);
}
