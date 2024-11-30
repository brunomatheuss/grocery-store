package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
import com.bruno.grocery_store.services.CartService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<CartResponseDTO>> getAllCarts() {
        List<CartResponseDTO> response = cartService.getAllCarts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartResponseDTO> getCartById(@PathVariable("cartId") Long cartId) {
        CartResponseDTO response = cartService.getCartById(cartId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> openCart() {
        CartResponseDTO response = cartService.openCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid AddProductRequestDTO request) {
        ProductResponseDTO response = cartService.addProduct(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("closeCart")
    public ResponseEntity<CloseCartResponseDTO> closeCart(@RequestBody CloseCartRequestDTO request) {
        CloseCartResponseDTO response = cartService.closeCart(request);
        return ResponseEntity.ok(response);
    }

}
