package com.system.gemini.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestMsgDto(
        @NotBlank(message = "Message must not be blank")
        String message
) {
}
