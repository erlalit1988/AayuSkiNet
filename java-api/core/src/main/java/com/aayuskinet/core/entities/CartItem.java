package com.aayuskinet.core.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String pictureUrl;
    private String brand;
    private String type;
}
