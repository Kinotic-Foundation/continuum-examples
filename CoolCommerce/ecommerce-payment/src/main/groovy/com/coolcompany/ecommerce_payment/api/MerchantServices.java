package com.coolcompany.ecommerce_payment.api;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Created by Navíd Mitchell 🤪 on 1/12/23.
 */
public interface MerchantServices {

    Mono<UUID> processPayment();

}
