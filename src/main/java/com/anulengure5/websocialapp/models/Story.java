package com.anulengure5.websocialapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data // Getter setter constructors
public class Story {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
   private  User user;

    private String image;

    private String caption ;

    private LocalDateTime timestamp;



}
