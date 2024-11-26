package com.chatterhub.dto;

import com.chatterhub.model.ChatRoomType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ChatRoomDTO {
    private Long id;
    private String name;
    private String description;
    private ChatRoomType type;
    private Set<UserDTO> members;
    private LocalDateTime createdAt;
} 