package com.aayuskinet.core.domain;

/**
 * Represents the base entity class that provides a common structure for all entities.
 * This class contains a unique identifier field and its corresponding getter and setter methods.
 */
public class BaseEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
