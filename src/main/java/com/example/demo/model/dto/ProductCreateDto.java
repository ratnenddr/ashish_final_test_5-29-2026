package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Product price is required")
    @Size(min = 1,message = "Product price cannot be 0 or negative")
    private Double productPrice;

    @NotNull(message = "Product quantity is required")
    @Size(min = 1,message = "Product stock quantity cannot be 0 or negative")
    private Long productStockQuantity;

}
