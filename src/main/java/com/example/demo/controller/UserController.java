package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String direction) {
        return new ResponseEntity<>(userService.getAllUsers(page, size, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.create(userCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, UserUpdateDto userUpdateDto) {
        return new ResponseEntity<>(userService.updateUser(id, userUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
