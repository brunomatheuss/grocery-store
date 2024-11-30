package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
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
    public List<CartResponseDTO> getAllCarts() {
        List<CartEntity> cartEntityList = cartRepository.findAll();
        return CartResponseDTO.convert(cartEntityList);
    }

    @Override
    public CartResponseDTO getCartById(Long cartId) {
        CartEntity cartEntity = cartRepository.findById(cartId).orElseThrow();
        return CartResponseDTO.convert(cartEntity);
    }

    @Override
    public CartResponseDTO openCart() {
        CartEntity cartEntity = CartEntity.builder().status("OPEN").totalValue(0L).build();
        cartEntity = cartRepository.save(cartEntity);
        return CartResponseDTO.convert(cartEntity);
    }

    @Override
    public ProductResponseDTO addProduct(AddProductRequestDTO request) {
        ProductDTO product = productService.getProductById(request.getProductId());
        CartEntity cart = cartRepository.findById(request.getCartId()).orElseThrow();
        if (cart.getStatus().equals("CLOSED")) {
            throw new RuntimeException("Cart is already closed!");
        }
        validateAndApplyPromotions(product, request.getQuantity());
        ProductEntity productEntity = buildProductEntity(request, product);
        cart.setTotalValue(cart.getTotalValue() + productEntity.getPrice());
        cartRepository.save(cart);
        productEntity = productRepository.save(productEntity);
        return ProductResponseDTO.convert(productEntity);
    }

    @Override
    public CloseCartResponseDTO closeCart(CloseCartRequestDTO request) {
        CartEntity cart = cartRepository.findById(request.getCartId()).orElseThrow();
        cart.setStatus("CLOSED");
        cart = cartRepository.save(cart);
        Long totalDiscount = cart.getProducts().stream().mapToLong(ProductEntity::getDiscount).sum();
        return CloseCartResponseDTO.convert(cart, totalDiscount);
    }

    private void validateAndApplyPromotions(ProductDTO product, Integer quantity) {
        product.getPromotions().forEach(promotion -> {
            if (promotion.getType().equals("BUY_X_GET_Y_FREE")) {
                if (quantity >= promotion.getRequiredQty()) {
                    Long discount = product.getPrice() * promotion.getFreeQty();
                    Long oldPrice = product.getPrice() * quantity;
                    Long newPrice = oldPrice - discount;
                    product.setDiscount(discount);
                    product.setOldPrice(oldPrice);
                    product.setPrice(newPrice);
                }
            } else if (promotion.getType().equals("FLAT_PERCENT")) {
                Long oldPrice = product.getPrice() * quantity;
                Double discount = (oldPrice * 0.1);
                Long newPrice = oldPrice - discount.longValue();
                product.setDiscount(discount.longValue());
                product.setOldPrice(oldPrice);
                product.setPrice(newPrice);
            } else if (promotion.getType().equals("QTY_BASED_PRICE_OVERRIDE")) {
                if (quantity.equals(promotion.getRequiredQty())) {
                    Long oldPrice = product.getPrice() * quantity;
                    Long newPrice = 1799L;
                    Long discount = oldPrice - newPrice;
                    product.setDiscount(discount);
                    product.setOldPrice(oldPrice);
                    product.setPrice(newPrice);
                }
            }
        });
    }

    private ProductEntity buildProductEntity(AddProductRequestDTO request, ProductDTO product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(request.getQuantity())
                .discount(product.getDiscount())
                .oldPrice(product.getOldPrice())
                .cartId(request.getCartId())
                .build();
    }

}
