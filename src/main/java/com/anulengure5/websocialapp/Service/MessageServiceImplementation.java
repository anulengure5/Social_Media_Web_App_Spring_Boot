package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.ChatRepository;
import com.anulengure5.websocialapp.Repository.MessageRepository;
import com.anulengure5.websocialapp.models.Chat;
import com.anulengure5.websocialapp.models.Message;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImplementation implements MessageService {

   @Autowired
  private MessageRepository messageRepository;

   @Autowired
   private ChatService chatService;

   @Autowired
   private ChatRepository chatRepository;

    @Autowired
    private ChatServiceImplementation chatServiceImplementation;


    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Message message = new Message();

        Chat chat=chatService.findChatById(chatId);


        message.setChat(chat);
        message.setContent(req.getContent());
        message.setUser(user);
        message.setImage(req.getImage());
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        chatRepository.save(chat);

        return savedMessage ;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat=chatService.findChatById(chatId);


        return messageRepository.findByChatId(chatId);
    }
}
