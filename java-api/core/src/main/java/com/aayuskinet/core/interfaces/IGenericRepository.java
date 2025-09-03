package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.BaseEntity;
import java.util.List;
import java.util.Optional;

public interface IGenericRepository<T extends BaseEntity> {
    Optional<T> getById(int id);
    List<T> listAll();
    Optional<T> getEntityWithSpec(ISpecification<T> spec);
    List<T> list(ISpecification<T> spec);
    int count(ISpecification<T> spec);
    void add(T entity);
    void update(T entity);
    void remove(T entity);
    boolean exists(int id);
}
