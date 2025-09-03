package com.aayuskinet.core.specifications;

import com.aayuskinet.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification extends BaseSpecification<Product> {

    public ProductSpecification(ProductSpecParams specParams) {
        super(createSpecification(specParams));
        applyPaging(specParams.getPageSize() * (specParams.getPageIndex() - 1), specParams.getPageSize());

        if (specParams.getSort() != null) {
            switch (specParams.getSort()) {
                case "priceAsc":
                    addOrderBy("price");
                    break;
                case "priceDesc":
                    addOrderByDescending("price");
                    break;
                default:
                    addOrderBy("name");
                    break;
            }
        }
    }

    private static Specification<Product> createSpecification(ProductSpecParams specParams) {
        return (root, query, criteriaBuilder) -> {
            // Initial predicate is always true
            var predicate = criteriaBuilder.conjunction();

            if (specParams.getSearch() != null && !specParams.getSearch().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                                "%" + specParams.getSearch() + "%"));
            }

            if (specParams.getBrands() != null && !specParams.getBrands().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        root.get("brand").in(specParams.getBrands()));
            }

            if (specParams.getTypes() != null && !specParams.getTypes().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        root.get("type").in(specParams.getTypes()));
            }

            return predicate;
        };
    }
}
