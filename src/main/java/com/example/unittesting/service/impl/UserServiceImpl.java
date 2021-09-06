package com.example.unittesting.service.impl;

import com.example.unittesting.core.ModelMapperUtils;
import com.example.unittesting.core.exceptions.CustomException;
import com.example.unittesting.model.enums.Status;
import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.model.response.PaginateResponse;
import com.example.unittesting.model.response.UserResponse;
import com.example.unittesting.persistence.entity.User;
import com.example.unittesting.persistence.repository.UserRepository;
import com.example.unittesting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 4:16 PM
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        userRepository.findByEmail(request.getUsername()).ifPresent(user -> {
            throw new CustomException(String.format("User with username %s already exist", request.getUsername()), HttpStatus.CONFLICT);
        });

        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new CustomException(String.format("User with email address %s already exist", request.getEmail()), HttpStatus.CONFLICT);
        });

        return ModelMapperUtils.map(userRepository.save(User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .empId(UUID.randomUUID().toString())
                .status(Status.ACTIVE.getStatus())
                .build()), UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserRequest request, Long id) {
        User user = userRepository.findById(id).<CustomException>orElseThrow(() -> {
            throw new CustomException("User info cannot be found", HttpStatus.NOT_FOUND);
        });

        if (!StringUtils.isBlank(request.getEmail())) {
            user.setEmail(request.getEmail());
        }

        if (!StringUtils.isBlank(request.getFirstName())) {
            user.setFirstName(request.getFirstName());
        }

        if (!StringUtils.isBlank(request.getLastName())) {
            user.setLastName(request.getLastName());
        }

        return ModelMapperUtils.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public Boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("User detail cannot be found", HttpStatus.NOT_FOUND);
        });
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserResponse fetchUser(Long id) {
        return ModelMapperUtils.map(userRepository.findById(id).<CustomException>orElseThrow(() -> {
            throw new CustomException("User identity cannot be found", HttpStatus.NOT_FOUND);
        }), UserResponse.class);
    }

    @Override
    public UserResponse fetchUser(String username) {
        return ModelMapperUtils.map(userRepository.findByUsername(username).<CustomException>orElseThrow(() -> {
            throw new CustomException("User identity cannot be found", HttpStatus.NOT_FOUND);
        }), UserResponse.class);
    }

    @Override
    public PaginateResponse<UserResponse> fetchUsers(int start, int limit) {
        Page<User> users = userRepository.findAll(PageRequest.of(start, limit));
        if (users.isEmpty()) {
            throw new CustomException("No user info is available", HttpStatus.NO_CONTENT);
        }
        return PaginateResponse.<UserResponse>builder()
                .content(ModelMapperUtils.mapAll(users.getContent(), UserResponse.class))
                .totalElements(users.getTotalElements())
                .build();
    }
}