package com.example.demo.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private Long productId;

    private String productName;

    private Double productPrice;

    private Long productStockQuantity;

    private Boolean productActive;

    private Boolean productDeleted;

    private LocalDateTime productCreatedAt;

    private LocalDateTime productUpdatedAt;

    private LocalDateTime productDeletedAt;

}
