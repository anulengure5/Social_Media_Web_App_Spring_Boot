package com.anulengure5.websocialapp.Repository;

import com.anulengure5.websocialapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    public List<Message> findByChatId(Integer chatID);



}
