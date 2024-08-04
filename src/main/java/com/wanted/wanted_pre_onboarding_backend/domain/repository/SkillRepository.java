package com.wanted.wanted_pre_onboarding_backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobSkill;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;

public interface SkillRepository {
	void save(Skill skill);

	Optional<Skill> findByName(String name);

	void saveJobSkillList(List<JobSkill> jobSkillList);
}
