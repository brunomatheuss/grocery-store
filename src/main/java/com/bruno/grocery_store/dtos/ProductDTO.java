package com.bruno.grocery_store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;

    private String name;

    private Long price;

    private Long oldPrice;

    private Long discount;

    private List<PromotionDTO> promotions;

}

