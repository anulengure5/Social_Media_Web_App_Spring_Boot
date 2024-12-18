package com.anulengure5.websocialapp.models;
//DOWNLOAD INSTALL LOMBOK

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter  // No more need to write getter and setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reels {

 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 private Integer id;

 private String title;

 private String Video;

 @ManyToOne
 private User user;



}
