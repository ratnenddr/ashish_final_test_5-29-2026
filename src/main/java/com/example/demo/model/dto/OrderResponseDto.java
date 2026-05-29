package com.example.demo.model.dto;

import com.example.demo.model.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long orderId;

    private Double orderPrice;

    private Boolean orderActive;

    private Boolean orderDeleted;

    private OrderStatus orderStatus;

    private LocalDateTime orderCreatedAt;

    private LocalDateTime orderUpdatedAt;

    private LocalDateTime orderDeletedAt;

    private List<OrderItemDto> orderItems;
}
