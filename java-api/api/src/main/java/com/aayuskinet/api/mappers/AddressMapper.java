package com.aayuskinet.api.mappers;

import com.aayuskinet.api.dtos.AddressDto;
import com.aayuskinet.core.entities.Address;

public class AddressMapper {

    public static AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto dto = new AddressDto();
        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    public static Address toEntity(AddressDto addressDto) {
        if (addressDto == null) {
            throw new IllegalArgumentException("addressDto cannot be null");
        }
        Address entity = new Address();
        entity.setLine1(addressDto.getLine1());
        entity.setLine2(addressDto.getLine2());
        entity.setCity(addressDto.getCity());
        entity.setState(addressDto.getState());
        entity.setCountry(addressDto.getCountry());
        entity.setPostalCode(addressDto.getPostalCode());
        return entity;
    }

    public static void updateFromDto(Address address, AddressDto addressDto) {
        if (addressDto == null) {
            throw new IllegalArgumentException("addressDto cannot be null");
        }
        if (address == null) {
            throw new IllegalArgumentException("address cannot be null");
        }
        address.setLine1(addressDto.getLine1());
        address.setLine2(addressDto.getLine2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setPostalCode(addressDto.getPostalCode());
    }
}
