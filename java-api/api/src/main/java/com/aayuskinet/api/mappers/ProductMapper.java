package com.aayuskinet.api.mappers;

import com.aayuskinet.api.dtos.CreateProductDto;
import com.aayuskinet.core.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(CreateProductDto createProductDto);
}
