package com.coolcompany.ecommerce_gateway

import com.kinotic.continuum.api.annotations.EnableContinuum
import com.kinotic.continuum.gateway.api.annotations.EnableContinuumGateway
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableContinuum
@EnableContinuumGateway
class EcommerceGatewayApplication {

	static void main(String[] args) {
		SpringApplication.run(EcommerceGatewayApplication, args)
	}

}
