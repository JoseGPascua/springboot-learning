package com.example.demo.product.services;

import com.example.demo.Command;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service Class contains the business logic
 */

// @Service annotation tells Spring Boot that this is a service and allows for dependency injection
@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;

    // Constructor Inject of ProductRepository allows us to use free methods created by the JpaRepository library
    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Initially these were their own methods defined in the class, but we added a layer of abstraction
    // by creating an interface and having the execute method become one that must be overridden
    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
