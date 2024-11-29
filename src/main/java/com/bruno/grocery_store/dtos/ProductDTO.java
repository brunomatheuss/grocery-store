package com.bruno.grocery_store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDTO(
        String id,
        String name,
        Long price,
        List<PromotionDTO> promotions) {
}
