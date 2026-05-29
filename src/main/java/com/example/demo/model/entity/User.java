package com.example.demo.model.entity;


import com.example.demo.model.enums.OrderStatus;
import com.example.demo.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "active")
    Boolean active = true;

    @Column(name = "deleted")
    Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}

