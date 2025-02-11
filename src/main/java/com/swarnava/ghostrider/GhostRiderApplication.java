package com.swarnava.ghostrider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Swarnava Chakraborty
 * @since 2025-Feb
 */
@SpringBootApplication
@EnableAsync
public class GhostRiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhostRiderApplication.class, args);
	}

}
