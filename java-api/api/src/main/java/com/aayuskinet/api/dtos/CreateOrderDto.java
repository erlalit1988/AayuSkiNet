package com.aayuskinet.api.dtos;

import com.aayuskinet.core.entities.orderaggregate.PaymentSummary;
import com.aayuskinet.core.entities.orderaggregate.ShippingAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotBlank
    private String cartId;
    @NotNull
    private Integer deliveryMethodId;
    @NotNull
    private ShippingAddress shippingAddress;
    @NotNull
    private PaymentSummary paymentSummary;
}
