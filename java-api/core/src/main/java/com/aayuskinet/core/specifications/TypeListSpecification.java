package com.aayuskinet.core.specifications;

import com.aayuskinet.core.entities.Product;

public class TypeListSpecification extends BaseSpecificationWithResult<Product, String> {

    public TypeListSpecification() {
        super();
        addSelect(Product::getType);
        applyDistinct();
    }
}
