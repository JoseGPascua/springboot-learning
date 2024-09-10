package com.example.demo.product;

import com.example.demo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tells springboot that this is a repository for the database
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
