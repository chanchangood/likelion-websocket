package com.inspire12.likelionwebsocket.controller;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import com.inspire12.likelionwebsocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final MessageService messageService;

    // /app/chat.sendMessage 로 들어오는 메시지를 처리하여 /topic/public 로 전송
    @MessageMapping("/chat.sendMessage") // app 으로 온 요청을 여기서 받아서 sendTo로 topic으로 publish한다
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    // /app/chat.addUser 로 들어오는 메시지를 처리하여 /topic/public 로 전송
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        return messageService.createWelcomeMessage(chatMessage);
    }

    @MessageMapping("/chat.sendPrivateMessage")
    @SendTo("/user/queue/private")
    public ChatMessage callUser(@RequestParam String username, @RequestBody ChatMessage chatMessage) {
        return messageService.callUser(username, chatMessage);
    }
}
