package com.aayuskinet.core.entities.orderaggregate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ProductItemOrdered {
    private int productId;
    private String productName;
    private String pictureUrl;
}
