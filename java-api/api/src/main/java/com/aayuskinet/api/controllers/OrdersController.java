package com.aayuskinet.api.controllers;

import com.aayuskinet.api.dtos.CreateOrderDto;
import com.aayuskinet.api.dtos.OrderDto;
import com.aayuskinet.common.mappers.OrderMapper;
import com.aayuskinet.api.security.SecurityUtils;
import com.aayuskinet.core.entities.DeliveryMethod;
import com.aayuskinet.core.entities.orderaggregate.Order;
import com.aayuskinet.core.interfaces.IDeliveryMethodRepository;
import com.aayuskinet.core.interfaces.IOrderRepository;
import com.aayuskinet.core.interfaces.IOrderService;
import com.aayuskinet.core.specifications.OrderSpecification;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class OrdersController extends BaseApiController {

    private final IOrderService orderService;
    private final IOrderRepository orderRepository; // Still needed for queries
    private final IDeliveryMethodRepository deliveryMethodRepository; // Still needed for queries
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderDto orderDto) {
        String email = SecurityUtils.getCurrentUserEmail();

        Order order = orderService.createOrder(email, orderDto.getCartId(), orderDto.getDeliveryMethodId(),
                orderDto.getShippingAddress(), orderDto.getPaymentSummary());

    // TODO: Implement mapping from Order to OrderDto using a proper mapper or manual mapping
    // Placeholder: return null or implement mapping logic here
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrdersForUser() {
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        OrderSpecification spec = new OrderSpecification(email);
        List<Order> orders = orderRepository.list(spec);

        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.notFound().build(); // No orders found for the user.
        }

    // TODO: Implement mapping from Order to OrderDto for list
    // Placeholder: return empty list or implement mapping logic here
    return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        OrderSpecification spec = new OrderSpecification(email, id);
        Optional<Order> orderOptional = orderRepository.getEntityWithSpec(spec);

        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Order not found
        }

    // TODO: Implement mapping from Order to OrderDto for single order
    return ResponseEntity.ok(null);
    }

    @GetMapping("/delivery-methods")
    public ResponseEntity<List<DeliveryMethod>> getDeliveryMethods() {
        List<DeliveryMethod> deliveryMethods = deliveryMethodRepository.listAll();
        return ResponseEntity.ok(deliveryMethods);
    }
}
