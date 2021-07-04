package com.thewalking.shop.security.model;

import lombok.Data;

@Data
public class AddressDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
