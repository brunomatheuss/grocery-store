package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("ProductController - getAllProducts - start");
        List<ProductDTO> response = productService.getAllProducts();
        log.info("ProductController - getAllProducts - end - response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") String productId) {
        log.info("ProductController - getProductById - start - productId: {}", productId);
        ProductDTO response = productService.getProductById(productId);
        log.info("ProductController - getProductById - end - responde: {}", response);
        return ResponseEntity.ok(response);
    }

}
