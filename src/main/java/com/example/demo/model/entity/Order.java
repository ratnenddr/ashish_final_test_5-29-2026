package com.example.demo.model.entity;

import com.example.demo.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    User userId;

    @Column(name = "ordered_at")
    LocalDateTime orderedAt;

    @Column(name = "total_amount")
    Double totalAmount;

    @Column(name = "active")
    Boolean active=true;

    @Column(name = "deleted")
    Boolean deleted=false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    OrderStatus status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "items")
    String items;
}
