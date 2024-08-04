package com.wanted.wanted_pre_onboarding_backend.infra.repositoryImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobSkill;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;
import com.wanted.wanted_pre_onboarding_backend.domain.repository.SkillRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaJobSkillRepository;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.JpaSkillRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class SkillRepositoryImpl implements SkillRepository {
	private final JpaSkillRepository jpaSkillRepository;
	private final JpaJobSkillRepository jpaJobSkillRepository;

	@Override
	public void save(Skill skill) {
		jpaSkillRepository.save(skill);
	}

	@Override
	public Optional<Skill> findByName(String name) {
		return jpaSkillRepository.findByName(name);
	}

	@Override
	public void saveJobSkillList(List<JobSkill> jobSkillList) {
		jpaJobSkillRepository.saveAll(jobSkillList);
	}

}
