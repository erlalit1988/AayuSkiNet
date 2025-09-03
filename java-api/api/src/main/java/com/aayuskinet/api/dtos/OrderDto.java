package com.aayuskinet.api.dtos;

import com.aayuskinet.core.entities.orderaggregate.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderDto {
    private int id;
    private OffsetDateTime orderDate;
    private String buyerEmail;
    private ShippingAddressDto shippingAddress;
    private String deliveryMethod;
    private BigDecimal shippingPrice;
    private PaymentSummaryDto paymentSummary;
    private List<OrderItemDto> orderItems;
    private BigDecimal subtotal;
    private String paymentIntentId;
    private String status; // Using String for status as in C# DTO
    private BigDecimal total;
}
