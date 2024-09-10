package com.example.demo.product.services;

import com.example.demo.Query;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList());
    }
}
