package com.example.demo.product.services;

import com.example.demo.Command;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Class contains the business logic
 */

// @Service annotation tells Spring Boot that this is a service and allows for dependency injection
@Service
public class DeleteProductService implements Command<Integer, Void> {

    private ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // We can create our runtime exception this way or make a class to do that for us
        throw new RuntimeException("Product not found");
    }
}
