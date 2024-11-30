package com.bruno.grocery_store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {

    private String id;

    private String type;

    @JsonSetter("required_qty")
    private Integer requiredQty;

    @JsonSetter("free_qty")
    private Integer freeQty;

    private Long amount;

    private Long price;

}
