package com.coolcompany.ecommerce_payment

import org.kinotic.continuum.api.annotations.EnableContinuum
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableContinuum
class EcommercePaymentApplication {

	static void main(String[] args) {
		SpringApplication.run(EcommercePaymentApplication, args)
	}

}
