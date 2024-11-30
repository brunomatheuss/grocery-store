package com.bruno.grocery_store.services;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.PromotionDTO;
import com.bruno.grocery_store.services.impl.PromotionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.bruno.grocery_store.utils.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceImplTest {

    @InjectMocks
    PromotionServiceImpl promotionService;

    @Test
    @DisplayName("Should calculate BuyXGetYPromotion")
    void shouldCalculateBuyXGetYPromotion() {
        PromotionDTO promotion = buildCalculateBuyXGetYPromotion();
        ProductDTO product = buildProductDTO();
        product.setPromotions(List.of(promotion));

        promotionService.calculateBuyXGetYPromotion(promotion, product, 2);

        assertEquals(1000L, product.getDiscount());
        assertEquals(1000L, product.getPrice());
        assertEquals(2000L, product.getOldPrice());
        assertEquals(promotion.getType(), product.getAppliedPromotion());
    }

    @Test
    @DisplayName("Should calculate FlatPercentPromotion")
    void shouldCalculateFlatPercentPromotion() {
        PromotionDTO promotion = buildFlatPercentPromotion();
        ProductDTO product = buildProductDTO();
        product.setPromotions(List.of(promotion));

        promotionService.calculateFlatPercentPromotion(promotion, product, 2);

        assertNotNull(product);
        assertEquals(200L, product.getDiscount());
        assertEquals(1800L, product.getPrice());
        assertEquals(2000L, product.getOldPrice());
        assertEquals(promotion.getType(), product.getAppliedPromotion());
    }

    @Test
    @DisplayName("Should calculate QtyBasedPriceOverridePromotion")
    void shouldCalculateQtyBasedPriceOverridePromotion() {
        PromotionDTO promotion = buildQtyBasedPriceOverridePromotion();
        ProductDTO product = buildProductDTO();
        product.setPromotions(List.of(promotion));

        promotionService.calculateQtyBasedPriceOverridePromotion(promotion, product, 2);

        assertEquals(201L, product.getDiscount());
        assertEquals(1799L, product.getPrice());
        assertEquals(2000L, product.getOldPrice());
        assertEquals(promotion.getType(), product.getAppliedPromotion());
    }

}
