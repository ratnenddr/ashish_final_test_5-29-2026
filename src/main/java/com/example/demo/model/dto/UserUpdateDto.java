package com.example.demo.model.dto;

import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String userName;
    private String userEmail;

    private Role userRole;
}
