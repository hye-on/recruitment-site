package com.wanted.wanted_pre_onboarding_backend.infra.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;

@Repository
public interface JpaSkillRepository extends JpaRepository<Skill, UUID> {

	Optional<Skill> findByName(String name);
}
