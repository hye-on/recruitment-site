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
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Company extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String name;

	@Column(name = "country_name", nullable = false, length = 50)
	private String countryName;

	@Column(name = "location_name", nullable = false, length = 100)
	private String locationName;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Job> jobs = new ArrayList<>();

}
