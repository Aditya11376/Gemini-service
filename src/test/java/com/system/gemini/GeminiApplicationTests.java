package com.system.gemini;

import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import com.system.gemini.service.IGeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeminiApplicationTests {

	@Autowired
	private IGeminiService geminiService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGeminiGenerate() {
		ResponseMsgDto response = geminiService.generateAiResponse(new RequestMsgDto("Say hello"));
		System.out.println("Gemini Response: " + response.reply());
		assertThat(response).isNotNull();
		assertThat(response.reply()).isNotBlank();
	}

}
