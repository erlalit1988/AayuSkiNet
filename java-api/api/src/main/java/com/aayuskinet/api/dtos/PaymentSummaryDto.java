package com.aayuskinet.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentSummaryDto {
    @NotNull
    private Integer last4;
    @NotBlank
    private String brand;
    @NotNull
    private Integer expMonth;
    @NotNull
    private Integer expYear;
}
