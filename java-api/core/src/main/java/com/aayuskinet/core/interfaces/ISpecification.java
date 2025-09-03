package com.aayuskinet.core.interfaces;

import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public interface ISpecification<T> {
    Specification<T> getCriteria();
    List<String> getIncludes();
    String getOrderBy();
    String getOrderByDescending();
    int getTake();
    int getSkip();
    boolean isPagingEnabled();
    boolean isDistinct();
}
