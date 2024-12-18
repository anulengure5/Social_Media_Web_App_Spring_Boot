package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Request.CreateChatRequest;
import com.anulengure5.websocialapp.Service.ChatService;
import com.anulengure5.websocialapp.Service.UserService;
import com.anulengure5.websocialapp.models.Chat;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat
            (@RequestHeader("Authorization") String jwt,
             @RequestBody CreateChatRequest req) throws Exception {

        User reqUser=userService.findUserByJwt(jwt);
        User chatUser=userService.findUserById(req.getChatUserId());

        Chat chat=chatService.createChat(reqUser,chatUser);

        return chat;
    }


    @GetMapping ("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwt(jwt); //logged in user

        List<Chat> chat=chatService.findUsersChat(user.getId());

        return chat;
    }
}
