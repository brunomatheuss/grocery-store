package com.bruno.grocery_store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PromotionDTO(
        String id,
        String type,
        @JsonSetter("required_qty")
        Integer requiredQty,
        @JsonSetter("free_qty")
        Integer freeQty,
        Long amount,
        Long price) {
}
