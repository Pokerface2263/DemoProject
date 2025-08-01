package com.solvatech.onboarding.demoproject.mapper;

import com.solvatech.onboarding.demoproject.dto.UserResponseDTO;
import com.solvatech.onboarding.demoproject.model.User;

public class UserResponseMapper {

    public static UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setProfileImageUrl(user.getProfileImageUrl());
        return dto;
    }
}
