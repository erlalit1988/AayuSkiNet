package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.ShoppingCart;
import java.util.Optional;

public interface ICartService {
    Optional<ShoppingCart> getCart(String key);
    Optional<ShoppingCart> setCart(ShoppingCart cart);
    boolean deleteCart(String key);
}
