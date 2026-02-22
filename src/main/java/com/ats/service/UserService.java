package com.ats.service;

import com.ats.dto.UserRequest;
import com.ats.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    Page<UserResponse> getAllUsers(int page, int size, String sortBy);

    UserResponse getUserById(Long userId);

    void deleteUser(Long userId);
}
