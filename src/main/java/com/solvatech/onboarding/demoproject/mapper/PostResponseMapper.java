package com.solvatech.onboarding.demoproject.mapper;

import com.solvatech.onboarding.demoproject.dto.PostResponseDTO;
import com.solvatech.onboarding.demoproject.model.Post;
import com.solvatech.onboarding.demoproject.model.User;

public class PostResponseMapper {
    public static PostResponseDTO mapToDto(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());

        User user = post.getAuthor();
        dto.setAuthor(UserMapper.mapToDTO(user));

        return dto;
    }
}
