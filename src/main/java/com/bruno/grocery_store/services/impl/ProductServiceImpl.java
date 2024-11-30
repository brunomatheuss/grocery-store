package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.clients.WiremockClient;
import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final WiremockClient wiremockClient;

    @Override
    public List<ProductDTO> getAllProducts() {
        return wiremockClient.getAllProducts();
    }

    @Override
    public ProductDTO getProductById(String productId) {
        try {
            return wiremockClient.getProductById(productId);
        } catch (Exception e) {
            throw new RuntimeException("Product not found!");
        }
    }

}
