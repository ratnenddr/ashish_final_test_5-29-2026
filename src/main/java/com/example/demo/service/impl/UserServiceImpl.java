package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.*;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = getUser(id);
        if(userUpdateDto.getUserName() != null && !userUpdateDto.getUserName().isEmpty()) {
            user.setName(userUpdateDto.getUserName());
        }
        if(userUpdateDto.getUserEmail() != null && !userUpdateDto.getUserEmail().isEmpty()) {
            user.setName(userUpdateDto.getUserName());
        }
        if(userUpdateDto.getUserRole() != null) {
            user.setRole(userUpdateDto.getUserRole());
        }
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return CommonUtil.mapToUserDto(user);
    }

    @Override
    public UserResponseDto create(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getUserName());
        user.setName(userCreateDto.getUserName());
        user.setRole(userCreateDto.getUserRole());
        user.setActive(true);
        user.setDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return CommonUtil.mapToUserDto(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return CommonUtil.mapToUserDto(getUser(id));
    }

    @Override
    public Page<UserResponseDto> getAllUsers(int page,
                                             int size,
                                             String sortBy,
                                             String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable =
                PageRequest.of(page, size, sort);
        Page<User> users =
                userRepository.findAll(pageable);

        return users.map(CommonUtil::mapToUserDto);
    }

    @Override
    public String delete(Long id) {
        User user = getUser(id);
        user.setDeleted(true);
        user.setActive(false);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        return "User Deleted Successfully";
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User id not found with id {}", id);
            return new NotFoundException("User not found with id:" + id);
        });
    }
}
