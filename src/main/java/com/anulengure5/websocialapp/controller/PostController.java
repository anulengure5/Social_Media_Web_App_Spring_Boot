package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Response.ApiResponse;
import com.anulengure5.websocialapp.models.Post;
import com.anulengure5.websocialapp.models.User;
import com.anulengure5.websocialapp.Service.PostService;
import com.anulengure5.websocialapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping
public class PostController {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

  @PostMapping("/api/posts")
    public ResponseEntity<Post>createPost(@RequestHeader("Authorization")String jwt,@RequestBody Post post) throws Exception{

      User reqUser=userService.findUserByJwt(jwt);

      Post createdPost=postService.createNewPost(post, reqUser.getId());

      return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);

    }

@DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@RequestHeader("Authorization")String jwt, @PathVariable Integer postId) throws Exception{
    User reqUser=userService.findUserByJwt(jwt);

    String message=postService.deletePost(postId, reqUser.getId());
    ApiResponse apiResponse=new ApiResponse(message,true) ;

      return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
}

   @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post>findPostByIdHandler(@PathVariable Integer postId) throws Exception{

      Post post=postService.findPostById(postId);

      return new ResponseEntity<>(post, HttpStatus.ACCEPTED);

   }

   @GetMapping("/api/posts/user/{userId}")
   public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userId) throws Exception{
     List<Post> posts=postService.findPostByUserId(userId);
     return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
   }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>>findAllPost(@PathVariable Integer userId) throws Exception{
        List<Post> posts=postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post>savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
        User reqUser=userService.findUserByJwt(jwt);

        Post post=postService.savedPost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/posts/like/{postId}")
     public ResponseEntity<Post>likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);

        Post post = postService.likePost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }


}
