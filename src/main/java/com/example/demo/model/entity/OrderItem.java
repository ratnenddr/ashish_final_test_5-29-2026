package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product productId;

    @Column(name = "unit_price")
    Double price;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "active")
    Boolean active = true;

    @Column(name = "deleted")
    Boolean deleted = false;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}

