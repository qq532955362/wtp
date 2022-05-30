package org.example.user.pojo.request.fba.constant;

import lombok.Data;

@Data
public class Address {
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String districtOrCountry;
    private String stateOrRegion;
    private String postalCode;
    private String countryCode;
    private String phone;
}
