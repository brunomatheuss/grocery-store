package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.clients.WiremockClient;
import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.exceptions.GenericErrorException;
import com.bruno.grocery_store.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final WiremockClient wiremockClient;

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("ProductServiceImpl - getAllProducts");
        return wiremockClient.getAllProducts();
    }

    @Override
    public ProductDTO getProductById(String productId) {
        try {
            log.info("ProductServiceImpl - getProductById - productId: {}", productId);
            return wiremockClient.getProductById(productId);
        } catch (Exception e) {
            log.error("ProductServiceImpl - getProductById - error: ", e);
            //wiremock error
            throw new GenericErrorException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
