package com.chatterhub.controller;

import com.chatterhub.dto.MessageDTO;
import com.chatterhub.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        MessageDTO savedMessage = messageService.createMessage(
                messageDTO.getSenderId(),
                messageDTO.getChatRoomId(),
                messageDTO.getContent()
        );
        
        messagingTemplate.convertAndSend(
                String.format("/topic/chat.room.%s", messageDTO.getChatRoomId()),
                savedMessage
        );
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", messageDTO.getSenderUsername());
        headerAccessor.getSessionAttributes().put("userId", messageDTO.getSenderId());
        
        messagingTemplate.convertAndSend(
                String.format("/topic/chat.room.%s", messageDTO.getChatRoomId()),
                messageDTO
        );
    }
} 