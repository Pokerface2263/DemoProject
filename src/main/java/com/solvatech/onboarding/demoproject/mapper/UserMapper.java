package com.solvatech.onboarding.demoproject.mapper;

import com.solvatech.onboarding.demoproject.dto.UserDTO;
import com.solvatech.onboarding.demoproject.model.User;

public class UserMapper {

    public static User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setProfileImageUrl(userDTO.getProfileImageUrl());
        return user;
    }
}
