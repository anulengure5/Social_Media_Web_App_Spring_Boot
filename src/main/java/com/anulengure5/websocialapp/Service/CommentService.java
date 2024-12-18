package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.models.Comment;

public interface CommentService {

 public Comment createComment(Comment comment,Integer postId,Integer userId) throws Exception;
public Comment findCommentById(Integer commentId) throws Exception;
 public Comment likeComment(Integer CommentId,Integer UserId) throws Exception;


}
