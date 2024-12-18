package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.models.Chat;
import com.anulengure5.websocialapp.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User chatUser);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);


}
