package com.example.demo.service.impl;

import com.example.demo.exception.GenericException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ProductOutOfStockException;
import com.example.demo.model.dto.*;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.OrderItem;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommonUtil commonUtil;

    @Override
    public OrderResponseDto updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
        Order order = getOrder(id);
        if(orderUpdateDto.getOrderTotalAmount() != null && orderUpdateDto.getOrderTotalAmount() <= 0) {
            order.setTotalAmount(orderUpdateDto.getOrderTotalAmount());
        }

        if(orderUpdateDto.getOrderStatus() != null) {
            order.setStatus(orderUpdateDto.getOrderStatus());
        }
        order.setUpdatedAt(LocalDateTime.now());
        order = orderRepository.save(order);
        return CommonUtil.mapToOrderDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto create(OrderCreateDto request) {
        if(request.getOrderUserId()==null){
            throw new GenericException("User Id cannot be null",500);
        }
        User user=userRepository.findById(request.getOrderUserId()).orElseThrow(() -> {
            log.error("User id not found with id {}", request.getOrderUserId());
            return new NotFoundException("User not found with id:" + request.getOrderUserId());
        });
        Order order = new Order();
        order.setUserId(user);
        order.setOrderedAt(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;
        try {
            for (OrderDto itemDto : request.getOrderItems()) {
                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new NotFoundException("Product not found: " + itemDto.getProductId()));
                if (product.getStockQuantity() < itemDto.getQuantity()) {
                    throw new ProductOutOfStockException("Product " + product.getName() + " is out of stock");
                }
                product.setUnitSold(product.getUnitSold()+itemDto.getQuantity());
                product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity());
                productRepository.save(product);
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(product);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItem.setOrder(order);

                orderItems.add(orderItem);
                totalAmount += (product.getPrice() * itemDto.getQuantity());
            }
            order.setItems(orderItems.toString());
            order.setTotalAmount(totalAmount);
        }catch (ObjectOptimisticLockingFailureException e) {
            throw new GenericException("The product was updated by another user. Please try again.", 409);
        }

        return CommonUtil.mapToOrderDto(orderRepository.save(order));
    }


    @Override
    public OrderResponseDto getOrderById(Long id) {
        return CommonUtil.mapToOrderDto(getOrder(id));
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(int page,
                                               int size,
                                               String sortBy,
                                               String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable =
                PageRequest.of(page, size, sort);
        Page<Order> orders =
                orderRepository.findAll(pageable);

        return orders.map(CommonUtil::mapToOrderDto);
    }

    @Override
    public String delete(Long id) {
        Order order = getOrder(id);
        order.setDeleted(true);
        order.setActive(false);
        order.setDeletedAt(LocalDateTime.now());
        orderRepository.save(order);
        return "Order Deleted Successfully";
    }

    @Override
    public OrderResponseDto updateStatus(Long id, OrderUpdateDto updateDto) {
        Order order=getOrder(id);
        order.setStatus(updateDto.getOrderStatus());
        order.setUpdatedAt(LocalDateTime.now());
        return CommonUtil.mapToOrderDto(orderRepository.save(order));
    }

    @Override
    public OrderReportDto orderSumary(LocalDate from, LocalDate to) {
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.atStartOfDay();
        List<Order> orders=orderRepository.findOrdersInRange(start,end);
        OrderReportDto report = new OrderReportDto();
        report.setTotalOrders((long) orders.size());
        double totalRevenue = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
        report.setTotalRevenue(totalRevenue);
        Map<OrderStatus, Long> statusMap = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.counting()
                ));
        report.setOrdersByStatus(statusMap);
        List<Product> products=productRepository.findByUnitSold();
        for(Product product: products){
            report.setTopProducts(topProductAssign(product));
        }
        return report;
    }

    private TopProductDto topProductAssign(Product product) {
        TopProductDto productDto=new TopProductDto();
        productDto.setProductId(product.getId());
        productDto.setName(product.getName());
        productDto.setUnitsSold(product.getUnitSold());
        return productDto;
    }

    private Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(() -> {
            log.error("Order id not found with id {}", id);
            return new NotFoundException("Order not found with id:" + id);
        });
    }
}
