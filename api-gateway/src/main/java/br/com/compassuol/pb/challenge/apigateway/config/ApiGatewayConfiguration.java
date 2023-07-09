package br.com.compassuol.pb.challenge.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/users/**")
                        .uri("lb://ms-users"))
                .route(p -> p.path("/products/**")
                        .uri("lb://ms-products"))
                .route(p -> p.path("/oauth/**")
                        .uri("lb://ms-auth"))
                .build();
    }
}
