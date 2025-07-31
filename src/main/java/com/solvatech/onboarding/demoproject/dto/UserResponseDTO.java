package com.solvatech.onboarding.demoproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String profileImageUrl;
}
