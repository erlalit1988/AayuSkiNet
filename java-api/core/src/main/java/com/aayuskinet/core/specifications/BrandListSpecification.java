package com.aayuskinet.core.specifications;

import com.aayuskinet.core.entities.Product;

public class BrandListSpecification extends BaseSpecificationWithResult<Product, String> {

    public BrandListSpecification() {
        super();
        addSelect(Product::getBrand);
        applyDistinct();
    }
}
