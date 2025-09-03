package com.aayuskinet.core;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends BaseEntity {

    @Embedded
    private ProductItemOrdered itemOrdered;

    private double price;
    private int quantity;

    @ManyToOne
    private Order order;

    // Getters and setters

    public ProductItemOrdered getItemOrdered() {
        return itemOrdered;
    }

    public void setItemOrdered(ProductItemOrdered itemOrdered) {
        this.itemOrdered = itemOrdered;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
