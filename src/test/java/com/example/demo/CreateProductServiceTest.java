package com.example.demo;

import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductExistsWhenCreateProductServiceReturnProductDTO() {
        Product product = new Product();
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 chars long");
        product.setPrice(9.99);

        when(productRepository.save(product)).thenReturn(product);

        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED)
                .body(new ProductDTO(product)), response);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void givenProductDoesNotExistsWhenCreateProductServiceThrowProductNotValidException() {
        when(productRepository.save(any())).thenReturn(null);

        assertThrows(ProductNotValidException.class, () -> createProductService.execute(new Product()));
        verify(productRepository, times(0)).save(any());
    }
}
