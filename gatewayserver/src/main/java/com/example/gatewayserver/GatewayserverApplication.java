package com.example.gatewayserver;

import com.example.gatewayserver.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayserverApplication {

	@Autowired
	private AuthenticationFilter authenticationFilter;

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}


	@Bean
	public RouteLocator sheasRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

		// Custom routing


		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/sheas/patients/**")
						.filters( f -> f
								.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
								.rewritePath("/sheas/patients/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://PATIENTS"))

				.route(p -> p
						.path("/sheas/doctors/**")
						.filters( f -> f
								.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
								.rewritePath("/sheas/doctors/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://DOCTORS"))

				.route(p -> p
						.path("/sheas/health/**")
						.filters( f -> f
								.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
								.rewritePath("/sheas/health/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://PATIENTS"))

				.route(p -> p
						.path("/sheas/alerts/**")
						.filters( f -> f
								.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
								.rewritePath("/sheas/alerts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ALERTSERVICE"))

				.route(p -> p
				.path("/sheas/security/**")
				.filters( f -> f.rewritePath("/sheas/security/(?<segment>.*)","/${segment}")
						.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				.uri("lb://SECURITY")).build();





	}
}
