package com.aayuskinet.core.specifications;

import com.aayuskinet.core.interfaces.ISpecificationWithResult;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;

public class BaseSpecificationWithResult<T, R> extends BaseSpecification<T> implements ISpecificationWithResult<T, R> {

    private Function<T, R> select;

    public BaseSpecificationWithResult() {
        super();
    }

    public BaseSpecificationWithResult(Specification<T> criteria) {
        super(criteria);
    }

    @Override
    public Function<T, R> getSelect() {
        return select;
    }

    protected void addSelect(Function<T, R> select) {
        this.select = select;
    }
}
