package com.aayuskinet.api.extensions;

import com.aayuskinet.api.dtos.AddressDto;
import com.aayuskinet.core.entities.Address;

public class AddressMappingExtensions {
    public static AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }
    AddressDto dto = new AddressDto();
    dto.setLine1(address.getLine1());
    dto.setLine2(address.getLine2());
    dto.setCity(address.getCity());
    dto.setState(address.getState());
    dto.setPostalCode(address.getPostalCode());
    dto.setCountry(address.getCountry());
    return dto;
    }

    public static Address toEntity(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
    address.setLine1(dto.getLine1());
    address.setLine2(dto.getLine2());
    address.setCity(dto.getCity());
    address.setState(dto.getState());
    address.setPostalCode(dto.getPostalCode());
    address.setCountry(dto.getCountry());
    return address;
    }
}
