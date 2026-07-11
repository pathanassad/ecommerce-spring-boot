package com.asad.ecommerce.service;

import com.asad.ecommerce.model.Product;
import com.asad.ecommerce.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
}

public void updateProduct(int id, Product product, MultipartFile imageFile) throws IOException{

        Product existingProduct = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));

        existingProduct.setProductAvailable(product.isProductAvailable());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setReleaseDate(product.getReleaseDate());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setImageName(imageFile.getOriginalFilename());
        existingProduct.setImageType(imageFile.getContentType());
        existingProduct.setImageData(imageFile.getBytes());
        repo.save(existingProduct);
}

@Transactional
public void  deleteProduct(int id){
//        Product product = repo.findById(id)
//                .orElseThrow(()-> new RuntimeException("No Product found"));

        if (!repo.existsById(id)) {
                throw new RuntimeException("No Product found");
        }
      repo.deleteProductByIdNative(id);

}

    public List<Product> searchProducts(String keyword){
            return repo.searchProducts(keyword);
    }
}
