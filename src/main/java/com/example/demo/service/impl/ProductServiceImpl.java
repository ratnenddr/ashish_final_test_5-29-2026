package com.example.demo.service.impl;

import com.example.demo.exception.GenericException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.*;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;
import com.example.demo.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommonUtil commonUtil;

    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = getProduct(id);
        if (productUpdateDto.getProductPrice() == null || productUpdateDto.getProductPrice() <= 0) {
            product.setPrice(productUpdateDto.getProductPrice());
        }
        if (productUpdateDto.getProductName() == null || !productUpdateDto.getProductName().isEmpty()) {
            product.setName(productUpdateDto.getProductName());
        }
        if (productUpdateDto.getProductStockQuantity() == null || productUpdateDto.getProductStockQuantity() <= 0) {
            product.setStockQuantity(productUpdateDto.getProductStockQuantity());
        }
        product.setUpdatedAt(LocalDateTime.now());
        product = productRepository.save(product);
        return CommonUtil.mapToProductDto(product);
    }

    @Override
    public ProductResponseDto create(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setName(productCreateDto.getProductName());
        product.setPrice(productCreateDto.getProductPrice());
        product.setStockQuantity(productCreateDto.getProductStockQuantity());
        product.setUnitSold(0L);
        product.setActive(true);
        product.setDeleted(false);
        product.setCreatedAt(LocalDateTime.now());
        product = productRepository.save(product);
        return CommonUtil.mapToProductDto(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return CommonUtil.mapToProductDto(getProduct(id));
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page,
                                                   int size,
                                                   String sortBy,
                                                   String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable =
                PageRequest.of(page, size, sort);
        Page<Product> products =
                productRepository.findAll(pageable);

        return products.map(CommonUtil::mapToProductDto);
    }

    @Override
    public String delete(Long id) {
        Product product = getProduct(id);
        product.setDeleted(true);
        product.setActive(false);
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        return "Product Deleted Successfully";
    }

    @Override
    public ProductResponseDto updateStock(Long id, ProductUpdateDto productUpdateDto) {
        if (productUpdateDto.getUserId() == null) {
            throw new GenericException("User Id cannot be null", 500);
        }
        Role current = commonUtil.isAdminOrUser(id);
        if (current != Role.ADMIN) {
            throw new GenericException("Not authorized to perform this action", 401);
        }
        Product product = getProduct(id);
        product.setStockQuantity(productUpdateDto.getProductStockQuantity());
        product.setUpdatedAt(LocalDateTime.now());
        return CommonUtil.mapToProductDto(productRepository.save(product));
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            log.error("Product id not found with id {}", id);
            return new NotFoundException("Product not found with id:" + id);
        });
    }
}
