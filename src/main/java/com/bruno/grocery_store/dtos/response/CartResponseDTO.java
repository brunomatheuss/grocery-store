package com.bruno.grocery_store.dtos.response;

import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.entities.ProductEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private Long id;

    private String status;

    private Long totalValue;

    private List<ProductEntity> products;

    public static CartResponseDTO convert(CartEntity cartEntity) {
        return CartResponseDTO.builder()
                .id(cartEntity.getId())
                .status(cartEntity.getStatus())
                .totalValue(cartEntity.getTotalValue())
                .products(cartEntity.getProducts())
                .build();
    }

    public static List<CartResponseDTO> convert(List<CartEntity> cartEntityList) {
        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();
        cartEntityList.forEach(cartEntity -> cartResponseDTOList.add(CartResponseDTO.builder()
                .id(cartEntity.getId())
                .status(cartEntity.getStatus())
                .totalValue(cartEntity.getTotalValue())
                .products(cartEntity.getProducts())
                .build()));
        return cartResponseDTOList;
    }

}

