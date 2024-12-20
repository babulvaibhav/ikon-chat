package com.ikon.chat.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WebSocketAuthorizationFilter extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            // Extract sessionId from query params
            String sessionId = request.getQueryParams().getFirst("sessionId");
            if (sessionId == null) {
                return Mono.error(new RuntimeException("Missing session ID"));
            }

            // Fetch user-id from Redis
            String userId = redisTemplate.opsForValue().get("session:" + sessionId);

            if (userId == null) {
                return Mono.error(new RuntimeException("Unauthorized user"));
            }

            // Add user-id to the request headers
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("user-id", userId)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }
}
