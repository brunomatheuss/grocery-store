package com.bruno.grocery_store.dtos.response;

import com.bruno.grocery_store.entities.ProductEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private String id;

    private String name;

    private Long price;

    private Integer quantity;

    private Long discount;

    private String appliedPromotion;

    private Long unitPrice;

    private Long oldPrice;

    private Long cartId;

    public static ProductResponseDTO convert(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .discount(productEntity.getDiscount())
                .appliedPromotion(productEntity.getAppliedPromotion())
                .unitPrice(productEntity.getUnitPrice())
                .oldPrice(productEntity.getOldPrice())
                .cartId(productEntity.getCartId())
                .build();
    }

}

