package com.bruno.grocery_store.clients;

import com.bruno.grocery_store.dtos.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "wiremockClient", url = "${client.wiremock.url}")
public interface WiremockClient {

    @GetMapping("products")
    List<ProductDTO> getAllProducts();

    @GetMapping("products/{productId}")
    ProductDTO getProductById(@PathVariable("productId") String productId);

}
