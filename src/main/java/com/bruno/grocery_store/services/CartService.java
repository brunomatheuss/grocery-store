package com.bruno.grocery_store.services;

import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;

import java.util.List;

public interface CartService {

    List<CartEntity> getAllCarts();

    CartEntity openCart();

    ProductEntity addProductToCart(Long cartId, String productId, Integer quantity);

}
