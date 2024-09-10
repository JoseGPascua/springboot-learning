package com.example.demo.product;

import com.example.demo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Tells springboot that this is a repository for the database
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Spring Data JPA
    List<Product> findByNameContaining(String category);

    //JPQL
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword")String keyword);
}
