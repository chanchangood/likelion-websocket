package com.inspire12.likelionwebsocket.handshake;

import java.security.Principal;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
public class CustomHandShakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
        WebSocketHandler wsHandler,
        Map<String, Object> attributes) {
        String username = getUsernameFromRequest(request);

        return new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
    }

    private String getUsernameFromRequest(ServerHttpRequest request) {
        // URL 파라미터에서 토큰 추출
        String query = request.getURI().getQuery();
        if (query != null && query.contains("username=")) {
            return query.split("username=")[1];
        }
        return null;
    }

    private boolean validateToken(String token) {
        // JWT 토큰 검증 로직 구현 (JWT 라이브러리 이용)
        return true;
    }

    private String extractUsernameFromToken(String token) {
        // JWT 토큰에서 사용자 정보(username) 추출 로직 구현
        return token; // 예시로 간략히 처리
    }
}
