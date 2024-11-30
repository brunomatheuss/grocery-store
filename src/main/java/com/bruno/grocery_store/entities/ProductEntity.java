package com.bruno.grocery_store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DISCOUNT")
    private Long discount;

    @Column(name = "OLD_PRICE")
    private Long oldPrice;

    @Column(name = "CART_ID")
    private Long cartId;

}
