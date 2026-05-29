package com.example.demo.model.dto;

import com.example.demo.model.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

    @NotEmpty(message = "User Id is required")
    @Size(min = 1)
    private Long orderUserId;
    @NotEmpty(message = "Order price is required")
    @Size(min = 1,message = "Order total amount cannot be 0 or negative")
    private Long orderTotalAmount;

    private OrderStatus orderStatus;
    @NotNull(message = "Order time is required")
    private LocalDateTime orderCreateTime;

    private List<OrderDto> orderItems;
}
