package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import com.bruno.grocery_store.repositories.CartRepository;
import com.bruno.grocery_store.repositories.ProductRepository;
import com.bruno.grocery_store.services.CartService;
import com.bruno.grocery_store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    @Override
    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartEntity openCart() {
        CartEntity cart = CartEntity.builder().status("OPEN").totalValue(0L).build();
        return cartRepository.save(cart);
    }

    @Override
    public ProductEntity addProductToCart(Long cartId, String productId, Integer quantity) {
        ProductDTO product = productService.getProductById(productId);
        ProductEntity productEntity = ProductEntity.builder()
                .id(product.id())
                .name(product.name())
                .price(product.price())
                .quantity(quantity)
                .cartId(cartId)
                .build();
        return productRepository.save(productEntity);
    }

}
