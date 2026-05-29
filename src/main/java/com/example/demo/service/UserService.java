package com.example.demo.service;

import com.example.demo.model.dto.*;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserResponseDto> getAllUsers(int page, int size, String sortBy, String direction);

    UserResponseDto getUserById(Long id);

    UserResponseDto create(UserCreateDto orderCreateDto);

    UserResponseDto updateUser(Long id, UserUpdateDto orderUpdateDto);

    String delete(Long id);
}
