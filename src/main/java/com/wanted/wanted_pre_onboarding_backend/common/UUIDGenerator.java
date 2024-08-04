package com.wanted.wanted_pre_onboarding_backend.common;

import java.util.UUID;

import com.github.f4b6a3.ulid.UlidCreator;

public class UUIDGenerator {

	public static UUID generateId() {
		return UlidCreator.getMonotonicUlid().toUuid();
	}
}
