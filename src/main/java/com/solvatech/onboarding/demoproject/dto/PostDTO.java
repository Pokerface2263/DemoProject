package com.solvatech.onboarding.demoproject.dto;

import com.solvatech.onboarding.demoproject.mapper.UserMapper;
import com.solvatech.onboarding.demoproject.model.User;
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
