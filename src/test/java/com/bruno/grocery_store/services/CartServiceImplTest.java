package com.bruno.grocery_store.services;

import com.bruno.grocery_store.dtos.response.CartResponseDTO;
import com.bruno.grocery_store.dtos.response.CloseCartResponseDTO;
import com.bruno.grocery_store.dtos.response.ProductResponseDTO;
import com.bruno.grocery_store.entities.CartEntity;
import com.bruno.grocery_store.exceptions.GenericErrorException;
import com.bruno.grocery_store.repositories.CartRepository;
import com.bruno.grocery_store.repositories.ProductRepository;
import com.bruno.grocery_store.services.impl.CartServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.bruno.grocery_store.utils.TestHelper.*;
import static com.bruno.grocery_store.utils.TestHelper.buildProductDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductService productService;

    @Mock
    PromotionService promotionService;

    @InjectMocks
    CartServiceImpl cartService;

    @Test
    @DisplayName("Should get all carts")
    void shouldGetAllCarts() {
        CartEntity cartEntity = buildCartEntity();
        when(cartRepository.findAll()).thenReturn(List.of(cartEntity));

        List<CartResponseDTO> response = cartService.getAllCarts();

        assertNotNull(response);
        assertEquals(buildCartResponse().getId(), response.get(0).getId());
    }

    @Test
    @DisplayName("Should get cart by id")
    void shouldGetCartById() {
        CartEntity cartEntity = buildCartEntity();
        when(cartRepository.findById(any())).thenReturn(Optional.ofNullable(cartEntity));

        CartResponseDTO response = cartService.getCartById(1L);

        assertNotNull(response);
        assertEquals(buildCartResponse().getId(), response.getId());
    }

    @Test
    @DisplayName("Should get cart by id trow exception")
    void shouldGetCartByIdThrowException() {
        when(cartRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(GenericErrorException.class, () -> cartService.getCartById(1L));
    }

    @Test
    @DisplayName("Should open cart")
    void shouldOpenCart() {
        when(cartRepository.save(any())).thenReturn(buildCartEntity());

        CartResponseDTO response = cartService.openCart();

        assertNotNull(response);
        assertEquals(buildCartResponse().getId(), response.getId());
    }

    @Test
    @DisplayName("Should add product")
    void shouldAddProduct() {
        when(productService.getProductById(any())).thenReturn(buildProductDTO());
        when(cartRepository.findById(any())).thenReturn(Optional.ofNullable(buildCartEntity()));
        when(productRepository.save(any())).thenReturn(buildProductEntity());

        ProductResponseDTO response = cartService.addProduct(buildAddProductRequestDTO());

        assertNotNull(response);
        assertEquals(buildProductDTO().getId(), response.getId());
    }

    @Test
    @DisplayName("Should add product throw exception")
    void shouldAddProductThrowException() {
        CartEntity cartEntity = buildCartEntity();
        cartEntity.setStatus("CLOSED");
        when(productService.getProductById(any())).thenReturn(buildProductDTO());
        when(cartRepository.findById(any())).thenReturn(Optional.ofNullable(cartEntity));

        assertThrows(GenericErrorException.class, () -> cartService.addProduct(buildAddProductRequestDTO()));

    }

    @Test
    @DisplayName("Should close cart")
    void shouldCloseCart() {
        when(cartRepository.findById(any())).thenReturn(Optional.of(buildCartEntity()));
        when(cartRepository.save(any())).thenReturn(buildCartEntity());

        CloseCartResponseDTO response = cartService.closeCart(buildCloseCartRequestDTO());

        assertNotNull(response);
        assertEquals(buildCartEntity().getId(), response.getCartId());
    }

    @Test
    @DisplayName("Should close cart already closed")
    void shouldCloseCartThrowException() {
        CartEntity cartEntity = buildCartEntity();
        cartEntity.setStatus("CLOSED");
        when(cartRepository.findById(any())).thenReturn(Optional.of(cartEntity));

        CloseCartResponseDTO response = cartService.closeCart(buildCloseCartRequestDTO());

        assertNotNull(response);
        assertEquals(buildCartEntity().getId(), response.getCartId());
    }

}
