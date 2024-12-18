package com.anulengure5.websocialapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    private String content;
    private String image;


    @ManyToOne
    private User user;

   // @JsonIgnore
    @ManyToOne
    private Chat chat;

    private LocalDateTime timestamp;



}
