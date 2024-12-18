package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Service.MessageService;
import com.anulengure5.websocialapp.Service.UserService;
import com.anulengure5.websocialapp.models.Message;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/messages/chat/{chatId} ")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization")String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Message message=messageService.createMessage(user, chatId,req);

        return message;

    }

    @GetMapping("/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization")String jwt,
                                          @PathVariable Integer chatId) throws Exception {
        User user=userService.findUserByJwt(jwt);

         List<Message> messages=messageService.findChatsMessages(chatId);

        return messages;

    }



}
