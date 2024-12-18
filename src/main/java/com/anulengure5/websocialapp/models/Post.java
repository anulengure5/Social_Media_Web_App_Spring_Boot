package com.anulengure5.websocialapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Post")
public class Post {

 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 private Integer Id;

 private String caption;

 private String image;

 private String video;

@JsonIgnore
@ManyToOne
 private User user;  //Relationship between entities of entity set , Post table's  multiple posts will be related to a single user.
//goes into recursive problem solved with Json Ignore
 @OneToMany
 private List<User> liked=new ArrayList<>();   //one post like by many users

 private LocalDateTime createdAt;

 @OneToMany
private List<Comment>comments=new ArrayList<>();



}
