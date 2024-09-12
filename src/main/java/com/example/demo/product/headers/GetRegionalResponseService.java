package com.example.demo.product.headers;

import com.example.demo.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetRegionalResponseService {


    public ResponseEntity<String> execute(String region) {

        String response;

        if(region.equals("US")) {
            response = "UnitedStates";
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        if(region.equals("CAN")) {
            response = "Canada";
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }
}
