package com.aayuskinet.api.service;

import com.aayuskinet.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getProducts(String brand, String type, Pageable pageable);
    Product getProductById(Long id);
    List<String> getBrands();
    List<String> getTypes();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
