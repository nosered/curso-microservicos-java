package br.dev.edersonabreu.hrapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableEurekaClient
public class HrApiGatewayApplication {
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	    			.route(p -> p
	    				.path("/hr-worker/**")
	    				.filters(f -> f.rewritePath("/hr-worker/(?<path>.*)", "/$\\{path}"))
	    				.uri("lb://hr-worker")
	    			)
	    			.route(p -> p
	    				.path("/hr-payroll/**")
	    				.filters(f -> f.rewritePath("/hr-payroll/(?<path>.*)", "/$\\{path}"))
	    				.uri("lb://hr-payroll")
	    			)
	    			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(HrApiGatewayApplication.class, args);
	}

}
