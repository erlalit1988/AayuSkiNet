package com.aayuskinet.api.controllers;

import com.aayuskinet.api.dtos.CreateProductDto;
import com.aayuskinet.api.helpers.Pagination;
import com.aayuskinet.api.mappers.ProductMapper;
import com.aayuskinet.core.entities.Product;
import com.aayuskinet.core.interfaces.IProductRepository;
import com.aayuskinet.core.specifications.ProductSpecification;
import com.aayuskinet.core.specifications.ProductSpecParams;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController extends BaseApiController {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Pagination<Product>> getProducts(@ModelAttribute ProductSpecParams specParams) {
        ProductSpecification spec = new ProductSpecification(specParams);
        List<Product> products = productRepository.list(spec);
        int totalItems = productRepository.count(spec);
        Pagination<Product> pagination = new Pagination<>(specParams.getPageIndex(), specParams.getPageSize(), totalItems, products);
        return ResponseEntity.ok(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Optional<Product> product = productRepository.getById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        productRepository.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @Valid @RequestBody CreateProductDto productDto) {
        Optional<Product> productOptional = productRepository.getById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productMapper.toProduct(productDto);
        product.setId(id); // Set the id for update

        productRepository.update(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Optional<Product> product = productRepository.getById(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productRepository.remove(product.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands() {
        return ResponseEntity.ok(productRepository.getBrands());
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getTypes() {
        return ResponseEntity.ok(productRepository.getTypes());
    }
}
