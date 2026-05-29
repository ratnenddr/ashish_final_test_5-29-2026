package com.example.demo.model.dto;

import com.example.demo.model.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private Boolean userActive;
    private Boolean userDeleted;
    private Role userRole;
    private LocalDateTime userCreatedAt;
    private LocalDateTime userUpdatedAt;
    private LocalDateTime userDeletedAt;
}
