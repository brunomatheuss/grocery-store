package com.bruno.grocery_store.services;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;

import java.util.List;

public interface CartService {

    List<CartResponseDTO> getAllCarts();

    CartResponseDTO getCartById(Long cartId);

    CartResponseDTO openCart();

    ProductResponseDTO addProduct(AddProductRequestDTO request);

    CloseCartResponseDTO closeCart(CloseCartRequestDTO request);

}
