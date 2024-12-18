package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.CommentRepository;
import com.anulengure5.websocialapp.Repository.PostRepository;
import com.anulengure5.websocialapp.models.Comment;
import com.anulengure5.websocialapp.models.Post;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

 @Autowired
 private PostRepository postRepository;


    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {

       User user=userService.findUserById(userId);

       Post post=postService.findPostById(postId);

       comment.setUser(user);

       comment.setContent(comment.getContent());

       comment.setCreatedAt(LocalDateTime.now());

       Comment savedComment=commentRepository.save(comment);

       post.getComments().add(savedComment);

       postRepository.save(post);

       return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);

         if(opt.isEmpty())
         {
             throw new Exception ("Comment Does not exist !!!");
         }


        return opt.get();
    }

    @Override
    public Comment likeComment(Integer CommentId, Integer UserId) throws Exception {
         Comment comment= findCommentById(CommentId);
          User user =userService.findUserById(UserId);

          if(!comment.getLiked().contains(user))
          {
              comment.getLiked().add(user);
          }
          else comment.getLiked().remove(user);

           return commentRepository.save(comment);
    }
}
