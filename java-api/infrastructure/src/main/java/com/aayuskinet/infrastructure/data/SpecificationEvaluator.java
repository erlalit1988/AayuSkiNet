package com.aayuskinet.infrastructure.data;

import com.aayuskinet.core.interfaces.ISpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;

public class SpecificationEvaluator<T> {

    public static <T> TypedQuery<T> getQuery(EntityManager em, ISpecification<T> spec, Class<T> type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);

        if (spec.getCriteria() != null) {
            Predicate predicate = spec.getCriteria().toPredicate(root, cq, cb);
            cq.where(predicate);
        }

        if (spec.getIncludes() != null) {
            spec.getIncludes().forEach(include -> root.fetch(include));
        }

        if (spec.getOrderBy() != null) {
            cq.orderBy(cb.asc(root.get(spec.getOrderBy())));
        } else if (spec.getOrderByDescending() != null) {
            cq.orderBy(cb.desc(root.get(spec.getOrderByDescending())));
        }
        
        if (spec.isDistinct()){
            cq.distinct(true);
        }

        TypedQuery<T> query = em.createQuery(cq);

        if (spec.isPagingEnabled()) {
            query.setFirstResult(spec.getSkip());
            query.setMaxResults(spec.getTake());
        }

        return query;
    }
    
    public static <T> TypedQuery<Long> getCountQuery(EntityManager em, ISpecification<T> spec, Class<T> type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(type);

        if (spec.getCriteria() != null) {
            Predicate predicate = spec.getCriteria().toPredicate(root, cq, cb);
            cq.where(predicate);
        }
        
        if (spec.isDistinct()){
            cq.select(cb.countDistinct(root));
        } else {
            cq.select(cb.count(root));
        }

        return em.createQuery(cq);
    }
}
