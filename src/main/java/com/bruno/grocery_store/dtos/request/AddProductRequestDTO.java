package com.bruno.grocery_store.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequestDTO {

    @NotNull(message = "cartId is mandatory")
    private Long cartId;

    @NotNull(message = "productId is mandatory")
    private String productId;

    @NotNull(message = "quantity is mandatory")
    private Integer quantity;

}

