package com.inspire12.likelionwebsocket.controller;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatAdminController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/broadcast") // 이게 브로커 채널이다.
    public ChatMessage broadcastMessage(@RequestBody ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
        return chatMessage;
    }

}
