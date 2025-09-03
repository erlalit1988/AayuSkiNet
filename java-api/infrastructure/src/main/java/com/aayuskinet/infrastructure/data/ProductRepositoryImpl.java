package com.aayuskinet.infrastructure.data;
import com.aayuskinet.core.repositories.GenericRepository;

import com.aayuskinet.core.entities.Product;
import com.aayuskinet.core.interfaces.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends GenericRepository<Product> implements IProductRepository {

    public ProductRepositoryImpl() {
        super();
    }

    @Override
    public List<String> getBrands() {
        return entityManager.createQuery("SELECT DISTINCT p.productBrand FROM Product p ORDER BY p.productBrand", String.class).getResultList();
    }

    @Override
    public List<String> getTypes() {
        return entityManager.createQuery("SELECT DISTINCT p.productType FROM Product p ORDER BY p.productType", String.class).getResultList();
    }

    // Stub implementations for IGenericRepository
    @Override
    public java.util.Optional<Product> getById(int id) { return java.util.Optional.empty(); }
    @Override
    public java.util.List<Product> listAll() { return java.util.List.of(); }
    @Override
    public java.util.Optional<Product> getEntityWithSpec(com.aayuskinet.core.interfaces.ISpecification<Product> spec) { return java.util.Optional.empty(); }
    @Override
    public java.util.List<Product> list(com.aayuskinet.core.interfaces.ISpecification<Product> spec) { return java.util.List.of(); }
    @Override
    public int count(com.aayuskinet.core.interfaces.ISpecification<Product> spec) { return 0; }
    @Override
    public void add(Product entity) {}
    @Override
    public void update(Product entity) {}
    @Override
    public void remove(Product entity) {}
    @Override
    public boolean exists(int id) { return false; }
}
