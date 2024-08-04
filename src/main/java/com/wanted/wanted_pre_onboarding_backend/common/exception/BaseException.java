package com.wanted.wanted_pre_onboarding_backend.common.exception;

import com.wanted.wanted_pre_onboarding_backend.common.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {

	private StatusCode statusCode;

	public BaseException(String message, Throwable cause, StatusCode statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public BaseException(Throwable cause, StatusCode statusCode) {
		super(cause);
		this.statusCode = statusCode;
	}
}
