package com.bruno.grocery_store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_generator")
    @SequenceGenerator(name = "cart_generator", sequenceName = "cart_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TOTAL_VALUE")
    private Long totalValue;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

}
