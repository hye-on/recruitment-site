package com.wanted.wanted_pre_onboarding_backend.infra.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.User;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID> {
}
