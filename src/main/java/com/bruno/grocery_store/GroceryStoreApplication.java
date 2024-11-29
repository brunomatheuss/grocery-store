package com.bruno.grocery_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GroceryStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreApplication.class, args);
	}

}
