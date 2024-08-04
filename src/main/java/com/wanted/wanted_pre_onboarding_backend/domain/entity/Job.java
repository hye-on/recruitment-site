package com.wanted.wanted_pre_onboarding_backend.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Job extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Column(nullable = false, length = 100)
	private String position;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(name = "recruitment_bonus", nullable = false)
	private Integer recruitmentBonus;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobSkill> jobSkills = new ArrayList<>();

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobApplicationHistory> applicationHistories = new ArrayList<>();
}
