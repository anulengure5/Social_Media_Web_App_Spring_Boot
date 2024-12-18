package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.PostRepository;
import com.anulengure5.websocialapp.Repository.UserRepository;
import com.anulengure5.websocialapp.models.Post;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserService userService;


  @Autowired
  UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post=findPostById(postId);
        User user=userService.findUserById(userId);

        if(post.getUser().getId()!=user.getId()){
            throw new Exception("Can't delete another users post !!!");

        }
        postRepository.delete(post);
        return " # Post DELETE :-)";

    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post=postRepository.findById(postId);
        if(post.isEmpty())
        {
            throw new Exception("Post not found");
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);

         if(user.getSavedPosts().contains(post)){   // remove the saved post
             user.getSavedPosts().remove(post);

         }else user.getSavedPosts().add(post);

         userRepository.save(user);
         return post;


    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);

        if(post.getLiked().contains(user)) // removes the Like
        {
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);  // adds like
        }

        return postRepository.save(post);

    }
}
