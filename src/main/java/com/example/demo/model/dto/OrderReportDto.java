package com.example.demo.model.dto;

import com.example.demo.model.enums.OrderStatus;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReportDto {
    private Long totalOrders;
    private double totalRevenue;
    private Map<OrderStatus,Long> ordersByStatus;
    private TopProductDto topProducts;
}
