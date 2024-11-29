package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") String productId) {
        ProductDTO response = productService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

}
