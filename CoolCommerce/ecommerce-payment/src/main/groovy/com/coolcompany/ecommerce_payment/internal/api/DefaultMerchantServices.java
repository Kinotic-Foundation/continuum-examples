package com.coolcompany.ecommerce_payment.internal.api;

import com.coolcompany.ecommerce_payment.api.MerchantServices;
import com.coolcompany.ecommerce_payment.api.domain.CustomerInfo;
import com.coolcompany.ecommerce_payment.api.domain.PaymentInfo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Navíd Mitchell 🤪 on 1/24/23.
 */
@Component
public class DefaultMerchantServices implements MerchantServices {

    @Override
    public Mono<UUID> processPayment(CustomerInfo customerInfo, PaymentInfo paymentInfo, BigDecimal amount){
        return Mono.defer(() -> {

            if(amount.compareTo(BigDecimal.ZERO) <= 0) {
                return Mono.error(new IllegalArgumentException("Amount must be greater than zero"));
            }

            return Mono.just(UUID.randomUUID());
        });
    }

}
