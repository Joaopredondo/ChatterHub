package com.chatterhub.repository;

import com.chatterhub.model.ChatRoom;
import com.chatterhub.model.ChatRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByType(ChatRoomType type);
    
    @Query("SELECT cr FROM ChatRoom cr JOIN cr.members m WHERE m.id = :userId")
    List<ChatRoom> findByMemberId(Long userId);
    
    Optional<ChatRoom> findByNameAndType(String name, ChatRoomType type);
} 