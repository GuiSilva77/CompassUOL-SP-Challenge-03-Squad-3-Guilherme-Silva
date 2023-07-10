package br.com.compassuol.pb.challenge.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Path -> {} User-Agent -> {} IP address -> {}", exchange.getRequest().getPath(),
                exchange.getRequest().getHeaders().get("User-Agent"), exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());

        return chain.filter(exchange);
    }
}
