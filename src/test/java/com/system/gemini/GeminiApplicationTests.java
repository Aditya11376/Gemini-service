package com.system.gemini;

import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import com.system.gemini.service.IGeminiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeminiApplicationTests {

	@Autowired(required = false)
	private IGeminiService geminiService;

	@Test
	@DisplayName("Spring Application Context loads successfully")
	void contextLoads() {
		// Verifies dependency injection and Spring Boot wiring
	}

	@Test
	@DisplayName("Integration Test: Real call to Gemini API (Only executed when valid GEMINI_API_KEY is configured)")
	@EnabledIfEnvironmentVariable(named = "GEMINI_API_KEY", matches = "^AIzaSy[A-Za-z0-9_-]{20,}$")
	void testGeminiGenerate() {
		assertThat(geminiService).isNotNull();
		ResponseMsgDto response = geminiService.generateAiResponse(new RequestMsgDto("Say hello in one word"));
		System.out.println("Live Gemini-2.5-Flash Response: " + response.reply());
		assertThat(response).isNotNull();
		assertThat(response.reply()).isNotBlank();
	}

}
