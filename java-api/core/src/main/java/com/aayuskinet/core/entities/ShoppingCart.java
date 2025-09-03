package com.aayuskinet.core.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    private String id;
    private List<CartItem> items = new ArrayList<>();
    private Integer deliveryMethodId;
    private String clientSecret;
    private String paymentIntentId;

    public ShoppingCart(String id) {
        this.id = id;
    }
}
