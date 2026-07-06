package com.asad.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asad.ecommerce.model.Product;
import com.asad.ecommerce.service.ProductService;
import java.util.List;

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
    public List<Product> getAllProducts(){
        return service.getAllProducts();

    }
}

