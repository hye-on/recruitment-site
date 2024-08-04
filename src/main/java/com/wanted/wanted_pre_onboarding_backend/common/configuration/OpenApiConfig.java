package com.wanted.wanted_pre_onboarding_backend.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
class OpenApiConfig {

	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			.title("원티드 프리온보딩 백엔드 인턴쉽")
			.version("1.0.0")
			.description("원티드 프리온보딩 백엔드 인턴쉽 api 명세서 입니다.");

		return new OpenAPI()
			.addServersItem(new Server().url("").description("현재 서버"))
			.info(info)
			.components(new Components());
	}
}
