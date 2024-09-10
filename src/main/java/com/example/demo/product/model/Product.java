package com.example.demo.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * Note that the annotations @NotNull, @Size, and @PositiveOrZero are only used
 * for the Spring Started Validation. These annotations provide a message for
 * @handleProductNotValidConstraints and they will not be used because of the
 * Custom Validations created.
 */

@Entity // This maps a Java Class to MySQL
@Data // This comes from lombok, which allows us to not have to create Getters and Setters
@Table(name ="product")
public class Product {

    @Id // All tables in MySQL need a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generates id
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Size(min = 20, message = "Description must be 20 characters long")
    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "Price must not be negative")
    @Column(name = "price")
    private Double price;
}
