package com.aayuskinet.core.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

public abstract class GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public Optional<T> findById(Class<T> clazz, int id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public boolean exists(Class<T> clazz, int id) {
        return entityManager.find(clazz, id) != null;
    }
}
