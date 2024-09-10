package com.example.demo.product.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo.Command;
import com.example.demo.exceptions.ErrorMessages;
import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.validators.ProductValidator;
import io.micrometer.common.util.StringUtils;
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
        //validate before saving

        // ProductValidator.execute(product); can also be used in place of the validateProduct code

        validateProduct(product);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }

    /**
     * A method that runs a series of checks to validate whether the @Product has the correct properties
     * @Product describes an object that has a name, description and price
     *
     * @ProductValidator.java does the exact same thing, however I wanted to include this code to demonstrate
     * how we can have private methods to be used by the public method
     * */
    private static void validateProduct(Product product) {
        if(StringUtils.isEmpty(product.getName())) {
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if(product.getDescription().length() < 20) {
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }

        if(product.getPrice() < 0.00 || product.getPrice() == null) {
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }
    }

}
