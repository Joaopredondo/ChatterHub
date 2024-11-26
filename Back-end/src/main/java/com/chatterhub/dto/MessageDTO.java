package com.chatterhub.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long id;
    private Long senderId;
    private String senderUsername;
    private Long chatRoomId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 