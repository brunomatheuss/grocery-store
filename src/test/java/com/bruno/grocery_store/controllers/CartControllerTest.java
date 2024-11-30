package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
import com.bruno.grocery_store.services.CartService;
import com.bruno.grocery_store.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.bruno.grocery_store.utils.TestHelper.buildAddProductRequestDTO;
import static com.bruno.grocery_store.utils.TestHelper.buildCloseCartRequestDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CartControllerTest {

    @MockBean
    CartService cartService;

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should get all carts")
    void shouldGetAllCarts() throws Exception {
        when(cartService.getAllCarts()).thenReturn(List.of(CartResponseDTO.builder().build()));
        mockMvc.perform(get("/v1/carts"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get cart by id")
    void shouldGetCartById() throws Exception {
        when(cartService.getCartById(any())).thenReturn(CartResponseDTO.builder().build());
        mockMvc.perform(get("/v1/carts/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should open cart")
    void shouldOpenCart() throws Exception {
        when(cartService.openCart()).thenReturn(CartResponseDTO.builder().build());
        mockMvc.perform(post("/v1/carts"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should add product")
    void shouldAddProduct() throws Exception {
        when(cartService.addProduct(any())).thenReturn(ProductResponseDTO.builder().build());
        AddProductRequestDTO request = buildAddProductRequestDTO();
        mockMvc.perform(post("/v1/carts/addProduct")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should add product throw exception")
    void shouldAddProductThrowException() throws Exception {
        when(cartService.addProduct(any())).thenReturn(ProductResponseDTO.builder().build());
        AddProductRequestDTO request = AddProductRequestDTO.builder().build();
        mockMvc.perform(post("/v1/carts/addProduct")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should close cart")
    void shouldCloseCart() throws Exception {
        when(cartService.closeCart(any())).thenReturn(CloseCartResponseDTO.builder().build());
        CloseCartRequestDTO request = buildCloseCartRequestDTO();
        mockMvc.perform(post("/v1/carts/closeCart")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should close cart throw exception")
    void shouldCloseCartThrowException() throws Exception {
        when(cartService.closeCart(any())).thenReturn(CloseCartResponseDTO.builder().build());
        CloseCartRequestDTO request = CloseCartRequestDTO.builder().build();
        mockMvc.perform(post("/v1/carts/closeCart")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}
