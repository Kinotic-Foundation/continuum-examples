package com.coolcompany.ecommerce_main.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Navíd Mitchell 🤪 on 1/13/23.
 */
@Data
@NoArgsConstructor
public class CheckoutInfo {

    private CustomerInfo customerInfo;

    private PaymentInfo paymentInfo;

    private String shippingOption;

    private List<CartItem> cartItems;

}
