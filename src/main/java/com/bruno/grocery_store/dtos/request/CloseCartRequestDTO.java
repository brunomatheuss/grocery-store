package com.bruno.grocery_store.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloseCartRequestDTO {

    @NotNull(message = "cartId is mandatory")
    private Long cartId;

}

