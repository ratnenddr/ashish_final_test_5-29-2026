package com.example.demo.service;

import com.example.demo.model.dto.OrderCreateDto;
import com.example.demo.model.dto.OrderReportDto;
import com.example.demo.model.dto.OrderResponseDto;
import com.example.demo.model.dto.OrderUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderResponseDto updateOrder(Long id, OrderUpdateDto orderUpdateDto);

    OrderResponseDto create(OrderCreateDto orderCreateDto);

    OrderResponseDto getOrderById(Long id);

    Page<OrderResponseDto> getAllOrders(int page,
                                        int size,
                                        String sortBy,
                                        String direction);

    String delete(Long id);

    OrderResponseDto updateStatus(Long id, OrderUpdateDto updateDto);

    OrderReportDto orderSumary(LocalDate from, LocalDate to);
}
