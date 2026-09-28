package com.system.gemini.controller;

import com.system.gemini.dto.RequestMsgDto;
import com.system.gemini.dto.ResponseMsgDto;
import com.system.gemini.service.IGeminiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping({"/gemini"})
@RestController
public class GeminiController {

    private final IGeminiService igeminiService;

    @PostMapping("/generate")
    public ResponseEntity<ResponseMsgDto> ask(@RequestBody @Valid RequestMsgDto requestMsgDto){
        ResponseMsgDto responseDto = igeminiService.generateAiResponse(requestMsgDto);
        return ResponseEntity.ok(responseDto);
    }
}
