package com.chat.dao.repository;

import com.chat.dao.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
}
