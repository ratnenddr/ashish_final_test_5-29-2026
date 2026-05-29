package com.example.demo.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Long orderId;

    private Long productId;

    private Long price;

    private Long quantity;

    private Boolean active;

    private Boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
