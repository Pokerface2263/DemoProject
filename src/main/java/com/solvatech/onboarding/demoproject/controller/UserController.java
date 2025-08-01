package com.solvatech.onboarding.demoproject.controller;

import com.solvatech.onboarding.demoproject.dto.UserResponseDTO;
import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping("/new")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        User savedUser = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserResponseDTO user) {
        return userService.updateUser(id, user);
    }
}
