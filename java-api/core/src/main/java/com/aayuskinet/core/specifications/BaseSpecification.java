package com.aayuskinet.core.specifications;

import com.aayuskinet.core.interfaces.ISpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification<T> implements ISpecification<T> {

    private Specification<T> criteria;
    private List<String> includes = new ArrayList<>();
    private String orderBy;
    private String orderByDescending;
    private int take;
    private int skip;
    private boolean isPagingEnabled;
    private boolean isDistinct;

    public BaseSpecification() {
    }

    public BaseSpecification(Specification<T> criteria) {
        this.criteria = criteria;
    }

    public Specification<T> getCriteria() {
        return criteria;
    }

    @Override
    public List<String> getIncludes() {
        return includes;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public String getOrderByDescending() {
        return orderByDescending;
    }

    @Override
    public int getTake() {
        return take;
    }

    @Override
    public int getSkip() {
        return skip;
    }

    @Override
    public boolean isPagingEnabled() {
        return isPagingEnabled;
    }

    @Override
    public boolean isDistinct() {
        return isDistinct;
    }

    protected void addInclude(String include) {
        includes.add(include);
    }

    protected void addOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    protected void addOrderByDescending(String orderByDescending) {
        this.orderByDescending = orderByDescending;
    }

    protected void applyPaging(int skip, int take) {
        this.skip = skip;
        this.take = take;
        this.isPagingEnabled = true;
    }

    protected void applyDistinct() {
        this.isDistinct = true;
    }
}
