package com.coolcompany.ecommerce_payment.api;

import com.coolcompany.ecommerce_payment.api.domain.CustomerInfo;
import com.coolcompany.ecommerce_payment.api.domain.PaymentInfo;
import org.kinotic.continuum.api.annotations.Proxy;
import org.kinotic.continuum.api.annotations.Publish;
import org.kinotic.continuum.api.annotations.Version;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Merchant services interface
 *
 * Created by Navíd Mitchell 🤪 on 1/12/23.
 */
@Publish
@Proxy // Proxy means this service will be available to other continuum services
public interface MerchantServices {

    /**
     * Processes a customer payment
     * @param customerInfo customer information
     * @param paymentInfo payment information
     * @param amount amount to be paid
     * @return a unique identifier for the payment
     */
    Mono<UUID> processPayment(CustomerInfo customerInfo, PaymentInfo paymentInfo, BigDecimal amount);

}
