package com.aayuskinet.core;

import java.util.concurrent.Future;

public interface CartService {
    Future<ShoppingCart> getCart(String key);
    Future<ShoppingCart> setCart(ShoppingCart cart);
    Future<Boolean> deleteCart(String key);
}
