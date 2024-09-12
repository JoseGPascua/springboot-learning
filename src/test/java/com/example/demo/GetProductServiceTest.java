package com.example.demo;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {

    @Mock // what to mock the response of -> need this dependency to run the test
    private ProductRepository productRepository;

    @InjectMocks // the thing we are testing
    private GetProductService getProductService;

    @BeforeEach // things we need before the test runs to set up properly
    public void setup() {
        // initializes the repository and the service class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductExistsWhenGetProductServiceReturnProductDTO() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 chars long");
        product.setPrice(9.99);

        //When - technically still part of the given
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        //Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);

        //asserts the product repository was only called once
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void givenProductDoesNotExistWhenGetProductServiceThrowProductNotFoundException() {
        //Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When & Then
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }

}
