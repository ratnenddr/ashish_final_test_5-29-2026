package com.example.demo.model.dto;

import com.example.demo.model.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateDto {
    private Double orderTotalAmount;

    private OrderStatus orderStatus;
}
