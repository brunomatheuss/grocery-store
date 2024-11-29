package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import com.bruno.grocery_store.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartEntity>> getAllCarts() {
        List<CartEntity> response = cartService.getAllCarts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartEntity> openCart() {
        CartEntity response = cartService.openCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("addProduct")
    public ResponseEntity<ProductEntity> addProductToCart(@RequestParam("cartId") Long cartId,
                                                          @RequestParam("productId") String productId,
                                                          @RequestParam("quantity") Integer quantity) {
        ProductEntity response = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(response);
    }

}
