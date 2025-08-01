package com.solvatech.onboarding.demoproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {

    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Long authorId;
}
