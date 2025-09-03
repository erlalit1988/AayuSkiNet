package com.aayuskinet.core.entities.orderaggregate;

import com.aayuskinet.core.entities.BaseEntity;
import com.aayuskinet.core.entities.DeliveryMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "order" is a reserved keyword in SQL
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    private OffsetDateTime orderDate = OffsetDateTime.now();
    private String buyerEmail;

    @Embedded
    private ShippingAddress shippingAddress;

    @ManyToOne
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliveryMethod;

    @Embedded
    private PaymentSummary paymentSummary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(precision = 18, scale = 2)
    private BigDecimal subtotal;
    private String paymentIntentId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    public BigDecimal getTotal() {
        return subtotal.add(deliveryMethod.getPrice());
    }
}
