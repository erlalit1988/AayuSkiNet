package com.aayuskinet.api.controllers;

import com.aayuskinet.core.entities.ShoppingCart;
import com.aayuskinet.core.interfaces.ICartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseApiController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable String id) {
        Optional<ShoppingCart> cart = cartService.getCart(id);
        return ResponseEntity.ok(cart.orElseGet(() -> new ShoppingCart(id)));
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> updateCart(@RequestBody ShoppingCart cart) {
        Optional<ShoppingCart> updatedCart = cartService.setCart(cart);
        return updatedCart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null)); // Or a more specific error response
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        boolean result = cartService.deleteCart(id);
        if (!result) {
            return ResponseEntity.badRequest().build(); // Or a more specific error response
        }
        return ResponseEntity.ok().build();
    }
}
