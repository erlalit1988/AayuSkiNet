package com.aayuskinet.core;

import java.util.concurrent.Future;

public interface PaymentService {
    Future<ShoppingCart> createOrUpdatePaymentIntent(String cartId);
}
