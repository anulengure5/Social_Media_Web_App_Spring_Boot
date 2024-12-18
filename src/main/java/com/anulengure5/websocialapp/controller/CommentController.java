package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.models.Comment;
import com.anulengure5.websocialapp.models.User;
import com.anulengure5.websocialapp.Service.CommentService;
import com.anulengure5.websocialapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader ("Authorization") String jwt, @PathVariable Integer postId) throws Exception {

       User user= userService.findUserByJwt(jwt);
       Comment createdComment=commentService.createComment(comment,postId,user.getId());

       return createdComment;
    }



    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader ("Authorization") String jwt, @PathVariable Integer commentId) throws Exception {

        User user= userService.findUserByJwt(jwt);
        Comment likedComment=commentService.likeComment(commentId,user.getId());

        return likedComment;
    }
}
