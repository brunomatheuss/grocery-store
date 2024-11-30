package com.bruno.grocery_store.services.impl;

import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.dtos.PromotionDTO;
import com.bruno.grocery_store.services.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    @Override
    public void calculateBuyXGetYPromotion(PromotionDTO promotion, ProductDTO product, Integer quantity) {
        log.info("PromotionServiceImpl - calculateBuyXGetYPromotion - start - promotion: {}, product: {}, quantity: {}", promotion, product, quantity);
        if (quantity >= promotion.getRequiredQty()) {
            Long unitPrice = product.getPrice();
            Long discount = product.getPrice() * promotion.getFreeQty();
            Long oldPrice = product.getPrice() * quantity;
            Long newPrice = oldPrice - discount;
            product.setDiscount(discount);
            product.setOldPrice(oldPrice);
            product.setPrice(newPrice);
            product.setUnitPrice(unitPrice);
            product.setAppliedPromotion(promotion.getType());
            log.info("PromotionServiceImpl - calculateBuyXGetYPromotion - end - product: {}", product);
        }
    }

    @Override
    public void calculateFlatPercentPromotion(PromotionDTO promotion, ProductDTO product, Integer quantity) {
        log.info("PromotionServiceImpl - calculateFlatPercentPromotion - start - promotion: {}, product: {}, quantity: {}", promotion, product, quantity);
        Long unitPrice = product.getPrice();
        Double oldPrice = (product.getPrice().doubleValue()/100) * quantity;
        Double discount = (oldPrice * 0.1) * 100;
        oldPrice = oldPrice * 100;
        Long newPrice = oldPrice.longValue() - discount.longValue();
        product.setDiscount(discount.longValue());
        product.setOldPrice(oldPrice.longValue());
        product.setPrice(newPrice);
        product.setUnitPrice(unitPrice);
        product.setAppliedPromotion(promotion.getType());
        log.info("PromotionServiceImpl - calculateFlatPercentPromotion - end - product: {}", product);
    }

    @Override
    public void calculateQtyBasedPriceOverridePromotion(PromotionDTO promotion, ProductDTO product, Integer quantity) {
        log.info("PromotionServiceImpl - calculateQtyBasedPriceOverridePromotion - start - promotion: {}, product: {}, quantity: {}", promotion, product, quantity);
        if (quantity.equals(promotion.getRequiredQty())) {
            Long unitPrice = product.getPrice();
            Long oldPrice = product.getPrice() * quantity;
            Long newPrice = 1799L;
            Long discount = oldPrice - newPrice;
            product.setDiscount(discount);
            product.setOldPrice(oldPrice);
            product.setPrice(newPrice);
            product.setUnitPrice(unitPrice);
            product.setAppliedPromotion(promotion.getType());
            log.info("PromotionServiceImpl - calculateQtyBasedPriceOverridePromotion - end - product: {}", product);
        }
    }

}
