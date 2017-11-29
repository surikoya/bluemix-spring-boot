package org.bluemixtestr.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.bluemixtestr"})
@EnableAutoConfiguration(exclude={HealthIndicatorAutoConfiguration.class, EndpointAutoConfiguration.class})
public class HelloworldApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
