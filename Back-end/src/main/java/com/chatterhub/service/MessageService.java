package com.chatterhub.service;

import com.chatterhub.dto.MessageDTO;
import com.chatterhub.model.Message;
import com.chatterhub.repository.MessageRepository;
import com.chatterhub.repository.ChatRoomRepository;
import com.chatterhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public MessageDTO createMessage(Long senderId, Long chatRoomId, String content) {
        Message message = new Message();
        message.setSender(userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        message.setChatRoom(chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Sala de chat não encontrada")));
        message.setContent(content);
        
        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }
    
    public List<MessageDTO> getChatRoomMessages(Long chatRoomId) {
        return messageRepository.findByChatRoomIdOrderByCreatedAtDesc(chatRoomId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private MessageDTO convertToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setSenderUsername(message.getSender().getUsername());
        dto.setChatRoomId(message.getChatRoom().getId());
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setUpdatedAt(message.getUpdatedAt());
        return dto;
    }
} 