package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.ProductRestController.ProductRestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ProductRestController.prepopulate();
		SpringApplication.run(DemoApplication.class, args);
	}

}
