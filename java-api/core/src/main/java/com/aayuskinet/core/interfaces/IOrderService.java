package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.orderaggregate.Order;
import com.aayuskinet.core.entities.orderaggregate.ShippingAddress;
import com.aayuskinet.core.entities.orderaggregate.PaymentSummary;

public interface IOrderService {
    Order createOrder(String buyerEmail, String cartId, Integer deliveryMethodId, ShippingAddress shippingAddress, PaymentSummary paymentSummary);
}
