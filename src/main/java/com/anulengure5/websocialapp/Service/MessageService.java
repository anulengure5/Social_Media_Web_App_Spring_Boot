package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.models.Chat;

import com.anulengure5.websocialapp.models.Message;
import com.anulengure5.websocialapp.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
