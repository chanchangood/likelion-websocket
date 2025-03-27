package com.inspire12.likelionwebsocket.service;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import com.inspire12.likelionwebsocket.model.ChatMessage.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessage createWelcomeMessage(ChatMessage chatMessage) {
        ChatMessage welcomeMessage = ChatMessage.builder()
                .sender("System")
                .content(
                        String.format("""
                        %s 님이 들어왔습니다.
                        """, chatMessage.getSender()))
                .type(ChatMessage.MessageType.JOIN)
                .build();

        return welcomeMessage;
    }

    public ChatMessage callUser(String username , ChatMessage chatMessage) {
        ChatMessage privateChatMessageToSend = ChatMessage.builder()
            .sender(chatMessage.getSender())
            .type(MessageType.CHAT)
            .content("귓속말: " + chatMessage.getContent())
            .build();
        messagingTemplate.convertAndSendToUser(username, "/queue/private", privateChatMessageToSend);
        return chatMessage;
    }
}
