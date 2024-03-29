package com.coolcompany.ecommerce_payment_autoconfig;

import com.coolcompany.ecommerce_payment.PaymentApiLibrary;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * This is the autoconfiguration class for the payment api library
 * It is defined in a separate package because it must not be scanned by the spring context
 */
@AutoConfiguration
@Import(PaymentApiLibrary.class)
public class PaymentApiAutoConfiguration {

}
