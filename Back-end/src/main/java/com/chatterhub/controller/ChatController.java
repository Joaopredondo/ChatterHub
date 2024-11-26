package com.chatterhub.controller;

import com.chatterhub.dto.ChatRoomDTO;
import com.chatterhub.dto.MessageDTO;
import com.chatterhub.dto.UserDTO;
import com.chatterhub.service.ChatRoomService;
import com.chatterhub.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    @PostMapping("/rooms")
    public ResponseEntity<ChatRoomDTO> createChatRoom(@RequestBody ChatRoomDTO request) {
        ChatRoomDTO chatRoom = chatRoomService.createChatRoom(
                request.getName(),
                request.getDescription(),
                request.getType(),
                request.getMembers().stream().map(UserDTO::getId).collect(Collectors.toSet())
        );
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/rooms/user/{userId}")
    public ResponseEntity<List<ChatRoomDTO>> getUserChatRooms(@PathVariable Long userId) {
        List<ChatRoomDTO> chatRooms = chatRoomService.getUserChatRooms(userId);
        return ResponseEntity.ok(chatRooms);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<MessageDTO>> getChatRoomMessages(@PathVariable Long roomId) {
        List<MessageDTO> messages = messageService.getChatRoomMessages(roomId);
        return ResponseEntity.ok(messages);
    }
} 