package com.bruno.grocery_store.services;

import com.bruno.grocery_store.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(String productId);

}
