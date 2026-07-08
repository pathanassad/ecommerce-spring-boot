package com.asad.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asad.ecommerce.model.Product;
import com.asad.ecommerce.service.ProductService;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Welcome to ecommerce";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){
        Product product = service.getProductById(prodId);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}

