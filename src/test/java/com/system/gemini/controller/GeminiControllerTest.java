package com.system.gemini.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import com.system.gemini.exception.GeminiExceptionHandler;
import com.system.gemini.service.IGeminiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GeminiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IGeminiService geminiService;

    @InjectMocks
    private GeminiController geminiController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(geminiController)
                .setControllerAdvice(new GeminiExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /gemini/generate - returns 200 OK with AI response reply")
    void shouldReturnAiResponseSuccessfully() throws Exception {
        RequestMsgDto request = new RequestMsgDto("What is an acute stroke?");
        ResponseMsgDto mockResponse = new ResponseMsgDto("Acute stroke is a neurological emergency caused by loss of blood supply.");

        when(geminiService.generateAiResponse(any(RequestMsgDto.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/gemini/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reply").value("Acute stroke is a neurological emergency caused by loss of blood supply."));

        verify(geminiService).generateAiResponse(any(RequestMsgDto.class));
    }

    @Test
    @DisplayName("POST /gemini/generate - returns 400 Bad Request when message is blank")
    void shouldReturnBadRequestWhenMessageIsBlank() throws Exception {
        RequestMsgDto invalidRequest = new RequestMsgDto("");

        mockMvc.perform(post("/gemini/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
