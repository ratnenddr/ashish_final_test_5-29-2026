package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String direction) {
        return new ResponseEntity<>(productService.getAllProducts(page, size, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductCreateDto orderCreateDto) {
        return new ResponseEntity<>(productService.create(orderCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ProductResponseDto> getAllProducts(@PathVariable Long id, ProductUpdateDto orderUpdateDto) {
        return new ResponseEntity<>(productService.updateProduct(id, orderUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto> updateProductStock(@PathVariable Long id,ProductUpdateDto productUpdateDto) {
        return new ResponseEntity<>(productService.updateStock(id,productUpdateDto), HttpStatus.OK);
    }
}
