package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.services.CartService;
import com.bruno.grocery_store.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @MockBean
    CartService cartService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Should get all products")
    void shouldGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(ProductDTO.builder().build()));
        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get product by id")
    void shouldGetProductById() throws Exception {
        when(productService.getProductById(any())).thenReturn(ProductDTO.builder().build());
        mockMvc.perform(get("/v1/products/1"))
                .andExpect(status().isOk());
    }

}
