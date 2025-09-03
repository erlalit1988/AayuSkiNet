
package com.aayuskinet.common.mappers;

import org.mapstruct.Mapper;
import com.aayuskinet.core.entities.CartItem;
import com.aayuskinet.core.entities.orderaggregate.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderItem cartItemToOrderItem(CartItem cartItem);
}
