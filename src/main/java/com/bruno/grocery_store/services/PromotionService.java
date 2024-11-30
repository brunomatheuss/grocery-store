package com.bruno.grocery_store.services;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.PromotionDTO;

public interface PromotionService {

    void calculateBuyXGetYPromotion(PromotionDTO promotion, ProductDTO product, Integer quantity);

    void calculateFlatPercentPromotion(PromotionDTO promotion, ProductDTO product, Integer quantity);

    void calculateQtyBasedPriceOverridePromotion(PromotionDTO promotion, ProductDTO product, Integer quantity);

}
