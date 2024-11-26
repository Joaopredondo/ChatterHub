package com.chatterhub.dto;

import com.chatterhub.model.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String profilePicture;
    private UserRole role;
    private boolean isActive;
} 