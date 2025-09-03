package com.aayuskinet.core.entities.orderaggregate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PaymentSummary {
    private int last4;
    private String brand;
    private int expMonth;
    private int expYear;
}
