package com.wanted.wanted_pre_onboarding_backend.presentation;

import java.util.NoSuchElementException;

import org.hibernate.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.wanted.wanted_pre_onboarding_backend.common.StatusCode;
import com.wanted.wanted_pre_onboarding_backend.common.exception.BaseException;
import com.wanted.wanted_pre_onboarding_backend.presentation.dto.BaseResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({BaseException.class})
	protected BaseResponse<StatusCode> handleCustomException(BaseException exception) {
		log.debug("BaseResponse exception occurred: {}", exception.getMessage(), exception);
		exception.printStackTrace();

		return new BaseResponse<>(exception.getStatusCode());
	}

	@ExceptionHandler({
		MethodArgumentNotValidException.class,
		MissingServletRequestParameterException.class,
		MissingRequestHeaderException.class,
		IllegalArgumentException.class,
		TypeMismatchException.class,
		HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class,
		MultipartException.class,
	})
	protected BaseResponse<StatusCode> handleBadRequestException(Exception exception) {
		log.debug("Bad request exception occurred: {}", exception.getMessage(), exception);
		exception.printStackTrace();

		return new BaseResponse<>(StatusCode.BAD_REQUEST);
	}

	@ExceptionHandler({
		EntityNotFoundException.class,
		NoSuchElementException.class,
	})
	protected BaseResponse<StatusCode> handleNotFoundException(Exception exception) {
		log.debug("Not found exception occurred: {}", exception.getMessage(), exception);
		exception.printStackTrace();

		return new BaseResponse<>(StatusCode.NOT_FOUND);
	}

	@ExceptionHandler({Exception.class})
	protected BaseResponse<StatusCode> handleServerException(Exception exception) {
		log.error(
			"Unexpected exception occurred: {}",
			exception.getMessage(),
			exception
		);
		exception.printStackTrace();

		return new BaseResponse<>(StatusCode.INTERNAL_SERVER_ERROR);
	}
}
