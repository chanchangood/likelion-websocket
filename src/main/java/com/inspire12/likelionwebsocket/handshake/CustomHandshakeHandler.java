package com.inspire12.likelionwebsocket.handshake;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler handler,
        Map<String, Object> attributes) {

        String username = getTokenFromRequest(request);
        return () -> username;
        //validateToken(), extractUsernameFromToken() 생략
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
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
