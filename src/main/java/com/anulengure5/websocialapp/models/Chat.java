package com.anulengure5.websocialapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//superficial View for chat section the actual messages will come from  message entity

@Entity
@Data
public class Chat {
//  Only the logged in user can interact with his/ her friends
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String chat_name; //name of user

    private String chat_image; //user profile image

    @ManyToMany
    private List<User> users=new ArrayList<>(); // one chat with many users and one user is part of many chats

   private LocalDateTime timestamp;

   @OneToMany(mappedBy = "chat")
   private List<Message> messages=new ArrayList<>();

}
