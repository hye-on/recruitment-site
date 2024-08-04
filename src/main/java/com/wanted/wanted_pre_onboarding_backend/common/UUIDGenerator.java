package com.wanted.wanted_pre_onboarding_backend.common;

import java.util.UUID;

import com.github.f4b6a3.ulid.UlidCreator;

public class UUIDGenerator {

	private UUIDGenerator() {

	}

	private static final class UUIDGeneratorHolder {
		private static final UUIDGenerator instance = new UUIDGenerator();
	}

	public static UUIDGenerator getInstance() {
		return UUIDGeneratorHolder.instance;
	}

	public UUID generateId() {
		return UlidCreator.getMonotonicUlid().toUuid();
	}
}
