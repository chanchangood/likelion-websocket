package com.inspire12.likelionwebsocket.service;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class StompMessagingService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompMessagingService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendToTopic(ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend("/topic/public", chatMessage);
    }


    public void sendToUser(String username, ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend("/user/" + username + "/queue/private", chatMessage);
    }

//    public ChatMessage createWelcomeMessage(ChatMessage chatMessage) {
//        return ChatMessage
//    }
}
