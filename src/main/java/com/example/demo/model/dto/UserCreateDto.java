package com.example.demo.model.dto;

import com.example.demo.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotBlank
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String userEmail;

    private Role userRole;

}
