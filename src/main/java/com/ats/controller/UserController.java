package com.ats.controller;

import com.ats.dto.UserRequest;
import com.ats.dto.UserResponse;
import com.ats.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    Create user
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.ok(response);
    }

//    Get All users
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(userService.getAllUsers(page, size, sortBy));
    }

//    Get user by Id
    @GetMapping("/{Id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "Id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

//    Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(value = "id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted Successfully");
    }

}
