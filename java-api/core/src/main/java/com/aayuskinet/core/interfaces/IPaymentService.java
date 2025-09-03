package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.ShoppingCart;
import java.util.Optional;

public interface IPaymentService {
    Optional<ShoppingCart> createOrUpdatePaymentIntent(String cartId);
}
