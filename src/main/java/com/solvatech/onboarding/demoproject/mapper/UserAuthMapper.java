package com.solvatech.onboarding.demoproject.mapper;

import com.solvatech.onboarding.demoproject.dto.UserAuthDTO;
import com.solvatech.onboarding.demoproject.model.User;

public class UserAuthMapper {

    public static UserAuthDTO from(User user) {
        UserAuthDTO dto = new UserAuthDTO();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
