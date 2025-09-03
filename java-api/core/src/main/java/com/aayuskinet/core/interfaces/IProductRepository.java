package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.Product;

import java.util.List;

public interface IProductRepository extends IGenericRepository<Product> {
    List<String> getBrands();
    List<String> getTypes();
}
