package com.wanted.wanted_pre_onboarding_backend.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Skill extends BaseEntity {

	@Column(nullable = false, length = 100, unique = true)
	private String name;

	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobSkill> jobSkills = new ArrayList<>();

}
