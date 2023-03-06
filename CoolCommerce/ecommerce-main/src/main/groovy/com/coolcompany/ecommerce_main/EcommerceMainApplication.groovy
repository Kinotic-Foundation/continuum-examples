package com.coolcompany.ecommerce_main


import org.kinotic.continuum.api.annotations.EnableContinuum
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableContinuum
class EcommerceMainApplication {

	static void main(String[] args) {
		SpringApplication.run(EcommerceMainApplication, args)
	}

}
