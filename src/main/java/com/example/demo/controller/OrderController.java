package com.example.demo.controller;

import com.example.demo.model.dto.OrderCreateDto;
import com.example.demo.model.dto.OrderReportDto;
import com.example.demo.model.dto.OrderResponseDto;
import com.example.demo.model.dto.OrderUpdateDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String direction) {
        return new ResponseEntity<>(orderService.getAllOrders(page, size, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return new ResponseEntity<>(orderService.create(orderCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<OrderResponseDto> getAllOrders(@PathVariable Long id, OrderUpdateDto orderUpdateDto) {
        return new ResponseEntity<>(orderService.updateOrder(id, orderUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long id, OrderUpdateDto orderUpdateDto) {
        return new ResponseEntity<>(orderService.updateStatus(id,orderUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<OrderReportDto> getOrderSummary(@RequestParam LocalDate from,
                                                                @RequestParam LocalDate to) {
        return new ResponseEntity<>(orderService.orderSumary(from,to), HttpStatus.OK);
    }

}
