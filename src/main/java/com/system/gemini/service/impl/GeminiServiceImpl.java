package com.system.gemini.service.impl;

import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import com.system.gemini.service.IGeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements IGeminiService {

    private final ChatClient chatClient;

    @Override
    public ResponseMsgDto generateAiResponse(RequestMsgDto requestMsgDto) {
        String response = chatClient.prompt()
                .user(requestMsgDto.message())
                .call()
                .content();

        return new ResponseMsgDto(response);
    }
}
