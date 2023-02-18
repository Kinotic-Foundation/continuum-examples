package com.coolcompany.ecommerce_main.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Navíd Mitchell 🤪 on 1/13/23.
 */
@Data
@NoArgsConstructor
public class CustomerInfo {

    private String firstname;

    private String lastname;

    private String middlename;

    private String company;

    private String email;

    private String phone;

    private String street;

    private String country;

    private String city;

    private String stateProvince;

    private String postalCode;

}
