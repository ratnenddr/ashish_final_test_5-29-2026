package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String productName;
    private Double productPrice;
    private Long productStockQuantity;
    private Long userId;
}
