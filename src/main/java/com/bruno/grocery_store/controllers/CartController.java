package com.bruno.grocery_store.controllers;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
import com.bruno.grocery_store.services.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("v1/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> getAllCarts() {
        log.info("CartController - getAllCarts - start");
        List<CartResponseDTO> response = cartService.getAllCarts();
        log.info("CartController - getAllCarts - end - response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartResponseDTO> getCartById(@PathVariable("cartId") Long cartId) {
        log.info("CartController - getCartById - start - cartId: {}", cartId);
        CartResponseDTO response = cartService.getCartById(cartId);
        log.info("CartController - getCartById - end - response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> openCart() {
        log.info("CartController - openCart - start");
        CartResponseDTO response = cartService.openCart();
        log.info("CartController - openCart - end - response: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid AddProductRequestDTO request) {
        log.info("CartController - addProduct - start - request: {}", request);
        ProductResponseDTO response = cartService.addProduct(request);
        log.info("CartController - addProduct - end - response: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("closeCart")
    public ResponseEntity<CloseCartResponseDTO> closeCart(@RequestBody @Valid CloseCartRequestDTO request) {
        log.info("CartController - closeCart - start - request: {}", request);
        CloseCartResponseDTO response = cartService.closeCart(request);
        log.info("CartController - closeCart - end - response: {}", response);
        return ResponseEntity.ok(response);
    }

}
