package com.ats.service.impl;

import com.ats.dto.UserRequest;
import com.ats.dto.UserResponse;
import com.ats.entity.User;
import com.ats.exception.DuplicateResourceException;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.UserRepository;
import com.ats.service.UserService;
import com.ats.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    Ensure email is unique
    @Override
    public UserResponse createUser(UserRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public Page<UserResponse> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<User> usersPage = userRepository.findAll(pageable);

        return usersPage.map(UserMapper::toResponse);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
