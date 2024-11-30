package com.bruno.grocery_store.dtos.response;

import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloseCartResponseDTO {

    private Long cartId;

    private String status;

    private Long totalDiscount;

    private Long totalValue;

    private List<ProductEntity> products;

    public static CloseCartResponseDTO convert(CartEntity cartEntity, Long totalDiscount) {
        return CloseCartResponseDTO.builder()
                .cartId(cartEntity.getId())
                .status(cartEntity.getStatus())
                .totalDiscount(totalDiscount)
                .totalValue(cartEntity.getTotalValue())
                .products(cartEntity.getProducts())
                .build();
    }

}

