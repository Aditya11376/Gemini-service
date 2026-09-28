package com.system.gemini.service;

import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import jakarta.validation.Valid;

public interface IGeminiService {
    ResponseMsgDto generateAiResponse(@Valid RequestMsgDto requestMsgDto);
}
