package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import com.bruno.grocery_store.exceptions.GenericErrorException;
import com.bruno.grocery_store.repositories.CartRepository;
import com.bruno.grocery_store.repositories.ProductRepository;
import com.bruno.grocery_store.services.CartService;
import com.bruno.grocery_store.services.ProductService;
import com.bruno.grocery_store.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bruno.grocery_store.constants.CartStatusEnum.CLOSED;
import static com.bruno.grocery_store.constants.CartStatusEnum.OPEN;
import static com.bruno.grocery_store.constants.PromotionConstants.*;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final PromotionService promotionService;

    private CartEntity getCartEntity(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new GenericErrorException("Cart not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<CartResponseDTO> getAllCarts() {
        List<CartEntity> cartEntityList = cartRepository.findAll();
        return CartResponseDTO.convert(cartEntityList);
    }

    @Override
    public CartResponseDTO getCartById(Long cartId) {
        CartEntity cartEntity = getCartEntity(cartId);
        return CartResponseDTO.convert(cartEntity);
    }

    @Override
    public CartResponseDTO openCart() {
        CartEntity cartEntity = CartEntity.builder().status(OPEN.name()).totalValue(0L).build();
        cartEntity = cartRepository.save(cartEntity);
        return CartResponseDTO.convert(cartEntity);
    }

    @Override
    public ProductResponseDTO addProduct(AddProductRequestDTO request) {
        ProductDTO product = productService.getProductById(request.getProductId());
        CartEntity cart = getCartEntity(request.getCartId());
        if (cart.getStatus().equals(CLOSED.name())) {
            throw new GenericErrorException("Cart is already closed!", HttpStatus.BAD_REQUEST);
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
        CartEntity cart = getCartEntity(request.getCartId());
        if (cart.getStatus().equals(CLOSED.name())) {
            Long totalDiscount = getTotalDiscount(cart);
            return CloseCartResponseDTO.convert(cart, totalDiscount);
        }
        cart.setStatus(CLOSED.name());
        cart = cartRepository.save(cart);
        Long totalDiscount = getTotalDiscount(cart);
        return CloseCartResponseDTO.convert(cart, totalDiscount);
    }

    private Long getTotalDiscount(CartEntity cart) {
        return cart.getProducts().stream().filter(productEntity -> productEntity.getDiscount() != null).mapToLong(ProductEntity::getDiscount).sum();
    }

    private void validateAndApplyPromotions(ProductDTO product, Integer quantity) {
        product.getPromotions().forEach(promotion -> {
            switch (promotion.getType()) {
                case BUY_X_GET_Y_FREE_PROMOTION -> promotionService.calculateBuyXGetYPromotion(promotion, product, quantity);
                case FLAT_PERCENT_PROMOTION -> promotionService.calculateFlatPercentPromotion(promotion, product, quantity);
                case QTY_BASED_PRICE_OVERRIDE_PROMOTION -> promotionService.calculateQtyBasedPriceOverridePromotion(promotion, product, quantity);
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
                .appliedPromotion(product.getAppliedPromotion())
                .unitPrice(product.getUnitPrice())
                .oldPrice(product.getOldPrice())
                .cartId(request.getCartId())
                .build();
    }

}
