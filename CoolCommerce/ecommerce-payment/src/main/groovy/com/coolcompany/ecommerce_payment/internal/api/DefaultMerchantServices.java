package com.coolcompany.ecommerce_payment.internal.api;

import com.coolcompany.ecommerce_payment.api.MerchantServices;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Created by Navíd Mitchell 🤪 on 1/24/23.
 */
public class DefaultMerchantServices implements MerchantServices {

    @Override
    public Mono<UUID> processPayment() {

    }

}
