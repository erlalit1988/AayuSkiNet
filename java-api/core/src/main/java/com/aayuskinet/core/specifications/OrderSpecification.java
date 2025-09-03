package com.aayuskinet.core.specifications;

import com.aayuskinet.core.entities.orderaggregate.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification extends BaseSpecification<Order> {

    public OrderSpecification(String email) {
        super(createSpecification(email, null));
        addIncludes();
        addOrderByDescending("orderDate");
    }

    public OrderSpecification(String email, int id) {
        super(createSpecification(email, id));
        addIncludes();
        addOrderByDescending("orderDate");
    }

    private void addIncludes() {
        addInclude("deliveryMethod");
        addInclude("orderItems");
    }

    private static Specification<Order> createSpecification(String email, Integer id) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.equal(root.get("buyerEmail"), email);

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            return predicate;
        };
    }
}
