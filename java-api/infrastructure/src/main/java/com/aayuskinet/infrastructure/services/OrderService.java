package com.aayuskinet.infrastructure.services;

import com.aayuskinet.common.exceptions.NotFoundException;
import com.aayuskinet.common.mappers.OrderMapper;
//import com.aayuskinet.core.entities.CartItem;
import com.aayuskinet.core.entities.DeliveryMethod;
//import com.aayuskinet.core.entities.Product;
import com.aayuskinet.core.entities.ShoppingCart;
import com.aayuskinet.core.entities.orderaggregate.Order;
import com.aayuskinet.core.entities.orderaggregate.OrderItem;
import com.aayuskinet.core.entities.orderaggregate.ShippingAddress;
import com.aayuskinet.core.entities.orderaggregate.PaymentSummary;
import com.aayuskinet.core.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final ICartService cartService;
    private final IProductRepository productRepository;
    private final IDeliveryMethodRepository deliveryMethodRepository;
    private final IOrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(String buyerEmail, String cartId, Integer deliveryMethodId, ShippingAddress shippingAddress, PaymentSummary paymentSummary) {
        ShoppingCart cart = cartService.getCart(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        if (cart.getPaymentIntentId() == null || cart.getPaymentIntentId().isEmpty()) {
            throw new IllegalStateException("Cart has no payment intent");
        }

        List<OrderItem> items = cart.getItems().stream().map(cartItem -> {
            productRepository.getById(cartItem.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found in cart: " + cartItem.getProductName()));
            return orderMapper.cartItemToOrderItem(cartItem);
        }).collect(Collectors.toList());


        DeliveryMethod deliveryMethod = deliveryMethodRepository.getById(deliveryMethodId)
                .orElseThrow(() -> new NotFoundException("Delivery method not found"));

        Order order = new Order();
        order.setOrderItems(items);
        order.setShippingAddress(shippingAddress);
        order.setDeliveryMethod(deliveryMethod);
        order.setPaymentIntentId(cart.getPaymentIntentId());
        order.setSubtotal(items.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setPaymentSummary(paymentSummary);
        order.setBuyerEmail(buyerEmail);

        // Set the order for each order item
        items.forEach(item -> item.setOrder(order));

        orderRepository.add(order);

        // delete the cart
        cartService.deleteCart(cartId);

        return order;
    }
}
