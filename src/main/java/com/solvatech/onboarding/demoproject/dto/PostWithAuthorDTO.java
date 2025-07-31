package com.solvatech.onboarding.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostWithAuthorDTO {
    private Long postId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    private Long authorId;
    private String authorFirstName;
    private String authorLastName;
    private String authorProfileImageUrl;
}
