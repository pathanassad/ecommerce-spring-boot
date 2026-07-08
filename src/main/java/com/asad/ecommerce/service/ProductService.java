package com.asad.ecommerce.service;

import com.asad.ecommerce.model.Product;
import com.asad.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

public Product getProductById(int prodId){
        return repo.findById(prodId).orElse(null);
}
}
