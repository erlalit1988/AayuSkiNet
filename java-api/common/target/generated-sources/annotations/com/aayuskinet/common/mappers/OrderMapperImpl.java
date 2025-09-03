package com.aayuskinet.common.mappers;

import com.aayuskinet.core.entities.CartItem;
import com.aayuskinet.core.entities.orderaggregate.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-03T12:40:55+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderItem cartItemToOrderItem(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setPrice( cartItem.getPrice() );
        orderItem.setQuantity( cartItem.getQuantity() );

        return orderItem;
    }
}
