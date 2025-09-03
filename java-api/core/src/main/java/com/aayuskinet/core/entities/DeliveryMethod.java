package com.aayuskinet.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryMethod extends BaseEntity {
    private String shortName;
    private String deliveryTime;
    private String description;
    @Column(precision = 18, scale = 2)
    private BigDecimal price;
}
