package com.ikon.chat.WebsocketManagerService.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // Extract user ID (e.g., from query params, headers, or cookies)
        String userId = "550e8400-e29b-41d4-a716-446655440000";//extractUserIdFromRequest(request);

        // Return a custom Principal with the user ID
        return new StompPrincipal(userId);
    }

    private String extractUserIdFromRequest(ServerHttpRequest request) {
        // Example: Extract from query parameters
        List<String> userIds = request.getHeaders().get("user-id");
        if (userIds != null && !userIds.isEmpty()) {
            return userIds.get(0); // Use the first user ID from the header
        }
        // Fallback or error handling
        return "anonymous";
    }

    // Custom Principal class
    public static class StompPrincipal implements Principal {
        private final String name;

        public StompPrincipal(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
