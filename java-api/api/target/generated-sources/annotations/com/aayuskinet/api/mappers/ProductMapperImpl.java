package com.aayuskinet.api.mappers;

import com.aayuskinet.api.dtos.CreateProductDto;
import com.aayuskinet.core.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-03T12:42:00+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(CreateProductDto createProductDto) {
        if ( createProductDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( createProductDto.getName() );
        product.setDescription( createProductDto.getDescription() );
        product.setPrice( createProductDto.getPrice() );
        product.setPictureUrl( createProductDto.getPictureUrl() );
        product.setType( createProductDto.getType() );
        product.setBrand( createProductDto.getBrand() );
        product.setQuantityInStock( createProductDto.getQuantityInStock() );

        return product;
    }
}
