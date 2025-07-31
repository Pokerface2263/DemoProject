package com.solvatech.onboarding.demoproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private UserResponseDTO author;
}
