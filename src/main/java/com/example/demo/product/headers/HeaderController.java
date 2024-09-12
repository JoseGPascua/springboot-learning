package com.example.demo.product.headers;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    private final GetRegionalResponseService getRegionalResponseService;

    public HeaderController(final GetRegionalResponseService getRegionalResponseService) {
        this.getRegionalResponseService = getRegionalResponseService;
    }

    @GetMapping("/header")
    public ResponseEntity<String> getRegionalResponse(@RequestHeader(required = false,
            defaultValue = "US") String region) {
        return getRegionalResponseService.execute(region);
    }

    @GetMapping(value = "/header/product",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description larger than 20 characters");

        return ResponseEntity.ok(product);
    }
}
