package com.wanted.wanted_pre_onboarding_backend.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wanted.wanted_pre_onboarding_backend.common.StatusCode;
import com.wanted.wanted_pre_onboarding_backend.common.exception.BaseException;

import lombok.Getter;

@Getter
@JsonPropertyOrder({"httpStatusCode", "httpReasonPhrase", "message", "result"})
public class BaseResponse<T> {

	private final int httpStatusCode;
	private final String httpReasonPhrase;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;

	public BaseResponse(StatusCode statusCode) {
		this.httpStatusCode = statusCode.getHttpStatus().value();
		this.httpReasonPhrase = statusCode.getHttpStatus().getReasonPhrase();
		this.message = statusCode.getMessage();
	}

	public BaseResponse(StatusCode statusCode, T result) {
		this.httpStatusCode = statusCode.getHttpStatus().value();
		this.httpReasonPhrase = statusCode.getHttpStatus().getReasonPhrase();
		this.message = statusCode.getMessage();
		this.result = result;
	}

	public BaseResponse(BaseException exception) {
		this.httpStatusCode = exception.getStatusCode().getHttpStatus().value();
		this.httpReasonPhrase = exception.getStatusCode().getHttpStatus().getReasonPhrase();
		this.message = exception.getStatusCode().getMessage();
	}
}
