package com.solvatech.onboarding.demoproject.service;

import com.solvatech.onboarding.demoproject.dto.UserDTO;
import com.solvatech.onboarding.demoproject.dto.UserResponseDTO;
import com.solvatech.onboarding.demoproject.mapper.UserMapper;
import com.solvatech.onboarding.demoproject.mapper.UserResponseMapper;
import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;



    public ResponseEntity<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users =  userRepository.findAll()
                .stream()
                .map(UserResponseMapper::mapToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    public User createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<UserResponseDTO> updateUser(Long id, UserResponseDTO updatedUser) {
        return userRepository.findById(id)
                .map(currentUser -> {
                    currentUser.setFirstName(updatedUser.getFirstName());
                    currentUser.setLastName(updatedUser.getLastName());
                    currentUser.setProfileImageUrl(updatedUser.getProfileImageUrl());

                    User savedUser = userRepository.save(currentUser);
                    System.out.println(savedUser.getFirstName());
                    return UserResponseMapper.mapToDTO(savedUser);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
