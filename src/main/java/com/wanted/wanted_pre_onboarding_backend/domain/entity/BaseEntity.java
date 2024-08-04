package com.wanted.wanted_pre_onboarding_backend.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.wanted.wanted_pre_onboarding_backend.common.UUIDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

	@Id
	@Column(updatable = false, nullable = false)
	private UUID id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		id = UUIDGenerator.getInstance().generateId();
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

