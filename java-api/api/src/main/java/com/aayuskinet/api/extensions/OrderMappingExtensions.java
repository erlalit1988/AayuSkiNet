package com.aayuskinet.api.extensions;

import com.aayuskinet.api.dtos.OrderDto;
import com.aayuskinet.api.dtos.OrderItemDto;
import com.aayuskinet.api.dtos.ShippingAddressDto;
import com.aayuskinet.core.entities.orderaggregate.Order;
import com.aayuskinet.core.entities.orderaggregate.OrderItem;

import java.util.stream.Collectors;

public class OrderMappingExtensions {
    public static OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setBuyerEmail(order.getBuyerEmail());
        dto.setOrderDate(order.getOrderDate());
        dto.setShippingAddress(toShippingAddressDto(order.getShippingAddress()));
        dto.setDeliveryMethod(order.getDeliveryMethod().getShortName());
        dto.setShippingPrice(order.getDeliveryMethod().getPrice());
        dto.setOrderItems(order.getOrderItems().stream().map(OrderMappingExtensions::toDto).collect(Collectors.toList()));
        dto.setSubtotal(order.getSubtotal());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus().toString());
        return dto;
    }

    private static ShippingAddressDto toShippingAddressDto(com.aayuskinet.core.entities.orderaggregate.ShippingAddress address) {
        if (address == null) return null;
        ShippingAddressDto dto = new ShippingAddressDto();
        dto.setName(address.getName());
        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountry(address.getCountry());
        return dto;
    }

    public static OrderItemDto toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(orderItem.getItemOrdered().getProductId());
        dto.setProductName(orderItem.getItemOrdered().getProductName());
        dto.setPictureUrl(orderItem.getItemOrdered().getPictureUrl());
        dto.setPrice(orderItem.getPrice());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }
}
