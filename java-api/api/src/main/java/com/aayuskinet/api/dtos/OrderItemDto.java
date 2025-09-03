package com.aayuskinet.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private int productId;
    private String productName;
    private String pictureUrl;
    private BigDecimal price;
    private int quantity;
}
