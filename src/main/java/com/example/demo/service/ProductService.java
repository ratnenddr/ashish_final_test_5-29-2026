package com.example.demo.service;

import com.example.demo.model.dto.*;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponseDto> getAllProducts(int page, int size, String sortBy, String direction);

    ProductResponseDto getProductById(Long id);

    ProductResponseDto create(ProductCreateDto orderCreateDto);

    ProductResponseDto updateProduct(Long id, ProductUpdateDto orderUpdateDto);

    String delete(Long id);

    ProductResponseDto updateStock(Long id, ProductUpdateDto productUpdateDto);
}
