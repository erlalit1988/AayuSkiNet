package com.aayuskinet.core.entities.orderaggregate;

import com.aayuskinet.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

// @Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

    @Embedded
    private ProductItemOrdered itemOrdered;

    @Column(precision = 18, scale = 2)
    private BigDecimal price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
