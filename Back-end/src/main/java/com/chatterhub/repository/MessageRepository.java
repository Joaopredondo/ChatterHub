package com.chatterhub.repository;

import com.chatterhub.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId);
    List<Message> findBySenderIdAndChatRoomIdOrderByCreatedAtDesc(Long senderId, Long chatRoomId);
} 