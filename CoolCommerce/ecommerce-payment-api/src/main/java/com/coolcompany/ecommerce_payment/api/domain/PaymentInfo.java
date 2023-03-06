package com.coolcompany.ecommerce_payment.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Navíd Mitchell 🤪 on 1/13/23.
 */
@Data
@NoArgsConstructor
public class PaymentInfo {

    private String cardHolderName;

    private String cardNumber;

    private Integer month;

    private Integer year;

    private String cvv;

}
