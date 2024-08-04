package com.wanted.wanted_pre_onboarding_backend.infra.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobSkill;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;

import jakarta.persistence.criteria.Join;

public class JpaJobSpecification {

	public static Specification<Job> searchInAllFields(String searchKeyWord) {
		return (root, query, cb) -> {
			if (searchKeyWord == null || searchKeyWord.isEmpty()) {
				return null;
			}

			String containsLikePattern = getContainsLikePattern(searchKeyWord);
			Join<Job, Company> companyJoin = root.join("company");
			Join<Job, JobSkill> jobSkillJoin = root.join("jobSkills");
			Join<JobSkill, Skill> skillJoin = jobSkillJoin.join("skill");

			return cb.or(
				cb.like(cb.lower(companyJoin.get("name")), containsLikePattern),
				cb.like(cb.lower(companyJoin.get("countryName")), containsLikePattern),
				cb.like(cb.lower(companyJoin.get("locationName")), containsLikePattern),
				cb.like(cb.lower(root.get("position")), containsLikePattern),
				cb.like(cb.lower(root.get("description")), containsLikePattern),
				cb.like(cb.lower(root.get("recruitmentBonus").as(String.class)), containsLikePattern),
				cb.like(cb.lower(skillJoin.get("name")), containsLikePattern)
			);
		};
	}

	private static String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
}
