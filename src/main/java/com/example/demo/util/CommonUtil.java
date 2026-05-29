package com.example.demo.util;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.OrderReportDto;
import com.example.demo.model.dto.OrderResponseDto;
import com.example.demo.model.dto.ProductResponseDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class CommonUtil {

    private final UserRepository userRepository;

    public static OrderResponseDto mapToOrderDto(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .orderPrice(order.getTotalAmount())
                .orderStatus(order.getStatus())
                .orderUpdatedAt(order.getUpdatedAt())
                .orderCreatedAt(order.getCreatedAt())
                .orderActive(order.getActive())
                .orderDeleted(order.getDeleted())
                .orderDeletedAt(order.getDeletedAt())
                .build();
    }

    public static ProductResponseDto mapToProductDto(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productStockQuantity(product.getStockQuantity())
                .productDeleted(product.getDeleted())
                .productDeletedAt(product.getDeletedAt())
                .productUpdatedAt(product.getUpdatedAt())
                .productActive(product.getActive())
                .build();
    }

    public static UserResponseDto mapToUserDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .userName(user.getName())
                .userRole(user.getRole())
                .userActive(user.getActive())
                .userDeleted(user.getDeleted())
                .userCreatedAt(user.getCreatedAt())
                .userDeletedAt(user.getDeletedAt())
                .userUpdatedAt(user.getUpdatedAt())
                .build();
    }

    public Role isAdminOrUser(Long id){
        User user= userRepository.findById(id).orElseThrow(() -> {
            log.error("User id not found with id {}", id);
            return new NotFoundException("User not found with id:" + id);
        });
        if(user.getRole()== Role.ADMIN){
            return Role.ADMIN;
        }else if(user.getRole()==Role.USER){
            return Role.USER;
        }else{
            return Role.UNKNOWN;
        }
    }
}
