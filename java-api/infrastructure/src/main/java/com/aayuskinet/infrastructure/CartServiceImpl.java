

package com.aayuskinet.infrastructure;

import com.aayuskinet.core.entities.ShoppingCart;
import com.aayuskinet.core.interfaces.ICartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CartServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<ShoppingCart> getCart(String key) {
        try {
            String cartString = redisTemplate.opsForValue().get(key);
            if (cartString == null) {
                return Optional.empty();
            }
            return Optional.of(objectMapper.readValue(cartString, ShoppingCart.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ShoppingCart> setCart(ShoppingCart cart) {
        try {
            String cartString = objectMapper.writeValueAsString(cart);
            redisTemplate.opsForValue().set(cart.getId(), cartString);
            return Optional.of(cart);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteCart(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
