package com.solvatech.onboarding.demoproject.mapper;

import com.solvatech.onboarding.demoproject.dto.PostDTO;
import com.solvatech.onboarding.demoproject.dto.PostWithAuthorDTO;
import com.solvatech.onboarding.demoproject.model.Post;

public class PostMapper {

    public static PostDTO mapToDto(Post post) {
        PostDTO dto = new PostDTO();
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setAuthorId(post.getAuthor().getId());
        return dto;
    }

    public static PostWithAuthorDTO mapToDtoWithAuthor(Post post) {
        PostWithAuthorDTO dto = new PostWithAuthorDTO();
        dto.setPostId(post.getId());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());

        dto.setAuthorId(post.getAuthor().getId());
        dto.setAuthorFirstName(post.getAuthor().getFirstName());
        dto.setAuthorLastName(post.getAuthor().getLastName());
        dto.setAuthorProfileImageUrl(post.getAuthor().getProfileImageUrl());
        return dto;
    }
}
