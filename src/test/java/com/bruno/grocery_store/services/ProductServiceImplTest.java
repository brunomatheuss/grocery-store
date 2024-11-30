package com.bruno.grocery_store.services;

import com.bruno.grocery_store.clients.WiremockClient;
import com.bruno.grocery_store.dtos.ProductDTO;
import com.bruno.grocery_store.exceptions.GenericErrorException;
import com.bruno.grocery_store.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.bruno.grocery_store.utils.TestHelper.buildProductDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    WiremockClient wiremockClient;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @DisplayName("Should get all products")
    void shouldGetAllProducts() {
        ProductDTO product = buildProductDTO();
        when(wiremockClient.getAllProducts()).thenReturn(List.of(product));

        List<ProductDTO> response = productService.getAllProducts();

        assertNotNull(response);
        assertEquals(product.getId(), response.get(0).getId());
    }

    @Test
    @DisplayName("Should get product by id")
    void shouldGetProductById() {
        ProductDTO product = buildProductDTO();
        when(wiremockClient.getProductById(any())).thenReturn(product);

        ProductDTO response = productService.getProductById("test");

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());
    }

    @Test
    @DisplayName("Should get product by id throw Exception")
    void shouldGetProductByIdThrowException() {
        when(wiremockClient.getProductById(any())).thenThrow(new RuntimeException("Error test"));

        assertThrows(GenericErrorException.class, () -> productService.getProductById("test"));
    }

}
