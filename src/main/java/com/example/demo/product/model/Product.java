package com.example.demo.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // This maps a Java Class to MySQL
@Data // This comes from lombok, which allows us to not have to create Getters and Setters
@Table(name ="product")
public class Product {

    @Id // All tables in MySQL need a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generates id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
}
