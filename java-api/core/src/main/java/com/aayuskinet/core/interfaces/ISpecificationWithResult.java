package com.aayuskinet.core.interfaces;

import java.util.function.Function;

public interface ISpecificationWithResult<T, R> extends ISpecification<T> {
    Function<T, R> getSelect();
}
