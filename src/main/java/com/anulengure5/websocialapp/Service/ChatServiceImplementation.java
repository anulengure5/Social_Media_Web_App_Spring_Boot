package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.ChatRepository;
import com.anulengure5.websocialapp.models.Chat;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User chatUser) {

        Chat isExist=chatRepository.findChatByUsersId(chatUser,reqUser);
        if(isExist!=null)
        {
            return isExist;           // if chat already has been created in past so return same ...
        }
        Chat chat=new Chat();

        chat.getUsers().add(chatUser);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);

    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> opt= chatRepository.findById(chatId);
        if(opt.isEmpty())
        {
            throw new Exception ("chat not found with id - "+ chatId);

        }

        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {

        return chatRepository.findByUsersId(userId);
    }
}
