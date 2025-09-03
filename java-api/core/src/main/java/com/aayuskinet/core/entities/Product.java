package com.aayuskinet.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(precision = 18, scale = 2)
    private BigDecimal price;
    private String pictureUrl;
    private String type;
    private String brand;
    private int quantityInStock;
}
