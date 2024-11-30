package com.bruno.grocery_store.utils;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.PromotionDTO;
import com.bruno.grocery_store.dtos.request.AddProductRequestDTO;
import com.bruno.grocery_store.dtos.request.CloseCartRequestDTO;
import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.bruno.grocery_store.constants.PromotionConstants.*;

@UtilityClass
public class TestHelper {

    public static PromotionDTO buildFlatPercentPromotion() {
        return PromotionDTO.builder()
                .id("test")
                .type(FLAT_PERCENT_PROMOTION)
                .amount(10L)
                .build();
    }

    public static PromotionDTO buildCalculateBuyXGetYPromotion() {
        return PromotionDTO.builder()
                .id("test")
                .type(BUY_X_GET_Y_FREE_PROMOTION)
                .requiredQty(2)
                .freeQty(1)
                .build();
    }

    public static PromotionDTO buildQtyBasedPriceOverridePromotion() {
        return PromotionDTO.builder()
                .id("test")
                .type(QTY_BASED_PRICE_OVERRIDE_PROMOTION)
                .requiredQty(2)
                .price(1799L)
                .build();
    }

    public static ProductDTO buildProductDTO() {
        return ProductDTO.builder()
                .id("test")
                .name("test")
                .price(1000L)
                .promotions(List.of(buildFlatPercentPromotion()))
                .build();
    }

    public static CartEntity buildCartEntity() {
        return CartEntity.builder()
                .id(1L)
                .status("OPEN")
                .totalValue(0L)
                .products(List.of(buildProductEntity()))
                .build();
    }

    public static CartResponseDTO buildCartResponse() {
        return CartResponseDTO.builder()
                .id(1L)
                .status("OPEN")
                .totalValue(0L)
                .build();
    }

    public static AddProductRequestDTO buildAddProductRequestDTO() {
        return AddProductRequestDTO.builder()
                .cartId(1L)
                .productId("test")
                .quantity(2)
                .build();
    }

    public static ProductEntity buildProductEntity() {
        return ProductEntity.builder()
                .id("test")
                .price(100L)
                .quantity(2)
                .discount(10L)
                .build();
    }

    public static CloseCartRequestDTO buildCloseCartRequestDTO() {
        return CloseCartRequestDTO.builder()
                .cartId(1L)
                .build();
    }

}
