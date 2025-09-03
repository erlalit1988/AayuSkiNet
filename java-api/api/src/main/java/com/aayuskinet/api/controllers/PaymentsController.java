package com.aayuskinet.api.controllers;

import com.aayuskinet.core.entities.DeliveryMethod;
import com.aayuskinet.core.entities.ShoppingCart;
import com.aayuskinet.core.interfaces.IDeliveryMethodRepository;
import com.aayuskinet.core.interfaces.IPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController extends BaseApiController {

    private final IPaymentService paymentService;
    private final IDeliveryMethodRepository deliveryMethodRepository;

    public PaymentsController(IPaymentService paymentService, IDeliveryMethodRepository deliveryMethodRepository) {
        this.paymentService = paymentService;
        this.deliveryMethodRepository = deliveryMethodRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> createOrUpdatePaymentIntent(@PathVariable String cartId) {
        Optional<ShoppingCart> cart = paymentService.createOrUpdatePaymentIntent(cartId);

        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null)); // Problem with your cart
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("delivery-methods")
    public ResponseEntity<List<DeliveryMethod>> getDeliveryMethods() {
        List<DeliveryMethod> deliveryMethods = deliveryMethodRepository.listAll();
        return ResponseEntity.ok(deliveryMethods);
    }
}
