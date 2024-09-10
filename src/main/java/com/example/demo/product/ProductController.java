package com.example.demo.product;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import com.example.demo.product.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class listens for requests
 */

// @RestController tells Spring Boot that this class is a Rest Controller
@RestController
public class ProductController {

    private final CreateProductService createProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final SearchProductService searchProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;

    // Constructor Injection
    public ProductController(CreateProductService createProductService,
                             GetProductsService getProductsService,
                             GetProductService getProductService,
                             SearchProductService searchProductService,
                             UpdateProductService updateProductService,
                             DeleteProductService deleteProductService) {
        this.createProductService = createProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }

}
