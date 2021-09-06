package com.example.unittesting.service;

import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.model.response.PaginateResponse;
import com.example.unittesting.model.response.UserResponse;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 4:01 PM
 */
public interface UserService {

    public UserResponse createUser(UserRequest request);

    public UserResponse updateUser(UserRequest request, Long id);

    public Boolean deleteUser(Long id);

    public UserResponse fetchUser(Long id);

    public UserResponse fetchUser(String username);

    public PaginateResponse<UserResponse> fetchUsers(int start, int limit);
}