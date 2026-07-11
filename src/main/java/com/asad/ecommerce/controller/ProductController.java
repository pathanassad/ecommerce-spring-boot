package com.asad.ecommerce.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asad.ecommerce.model.Product;
import com.asad.ecommerce.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     @PostMapping("/product")
     public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
           try {
               Product product1 = service.addProduct(product, imageFile);
               return new ResponseEntity<>(HttpStatus.CREATED);
           }catch (Exception e){
               return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           }

     }

     @GetMapping("/product/{productId}/image")
     public ResponseEntity<?> getProductImageById(@PathVariable int productId){
        Product product = service.getProductById(productId);
        if (product == null)
        {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .body(imageFile);
     }

     @PutMapping("/product/{id}")
     public ResponseEntity<String> updateProduct(
             @PathVariable int id,
             @RequestPart Product product,
             @RequestPart MultipartFile imageFile) {
        try {
                service.updateProduct(id, product, imageFile);

                return new ResponseEntity<>("Product updated Successfully", HttpStatus.OK);
        }catch (IOException e){

            return new ResponseEntity<>("Failed to update the product", HttpStatus.BAD_REQUEST);
        }
     }

     @DeleteMapping("/product/{id}")
     public ResponseEntity<String> deleteProduct(@PathVariable int id){
           try {
               service.deleteProduct(id);
               return ResponseEntity.ok().body("Product Deleted Successfully");
           }catch (Exception e){
               return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

         }
     }

     @GetMapping("/products/search")
     public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
     }
}

