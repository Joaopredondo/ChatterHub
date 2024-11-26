package com.chatterhub.service;

import com.chatterhub.dto.ChatRoomDTO;
import com.chatterhub.dto.UserDTO;
import com.chatterhub.model.ChatRoom;
import com.chatterhub.model.ChatRoomType;
import com.chatterhub.model.User;
import com.chatterhub.repository.ChatRoomRepository;
import com.chatterhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public ChatRoomDTO createChatRoom(String name, String description, ChatRoomType type, Set<Long> memberIds) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setDescription(description);
        chatRoom.setType(type);
        
        Set<User> members = memberIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id)))
                .collect(Collectors.toSet());
        
        chatRoom.setMembers(members);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return convertToDTO(savedChatRoom);
    }
    
    public List<ChatRoomDTO> getUserChatRooms(Long userId) {
        return chatRoomRepository.findByMemberId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private ChatRoomDTO convertToDTO(ChatRoom chatRoom) {
        ChatRoomDTO dto = new ChatRoomDTO();
        dto.setId(chatRoom.getId());
        dto.setName(chatRoom.getName());
        dto.setDescription(chatRoom.getDescription());
        dto.setType(chatRoom.getType());
        dto.setCreatedAt(chatRoom.getCreatedAt());
        dto.setMembers(chatRoom.getMembers().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toSet()));
        return dto;
    }
    
    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setRole(user.getRole());
        dto.setActive(user.isActive());
        return dto;
    }
} 