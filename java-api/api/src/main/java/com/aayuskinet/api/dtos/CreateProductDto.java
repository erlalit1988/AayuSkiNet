package com.aayuskinet.api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;
    @NotBlank
    private String pictureUrl;
    @NotBlank
    private String type;
    @NotBlank
    private String brand;
    @Min(value = 1, message = "Quantity in stock must be at least 1")
    private int quantityInStock;
}
