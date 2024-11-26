package com.chatterhub.dto;

import lombok.Data;

@Data
public class ChatNotification {
    private String status;
    private String sender;
    private String content;
    private Long chatRoomId;
} 