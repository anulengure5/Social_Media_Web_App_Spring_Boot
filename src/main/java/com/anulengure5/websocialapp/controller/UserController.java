package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Repository.UserRepository;
import com.anulengure5.websocialapp.models.User;
import com.anulengure5.websocialapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

     //BECOMES PART OF AUTH CONTROLLER FOR AUTHENTICATION Purposes
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user)
//    {
//        User savedUser=userService.registerUser(user);
//
//        return savedUser;
//    }
    @GetMapping("/users")
    public List<User> getUsers()
    {
        List<User> users=userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {

        User user=userService.findUserById(userId);
        return user;

    }

//    @PostMapping("/userPost")
//    public User createUserPost(@RequestBody User user)
//    {
////        User newUser=new User(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(), user.getPassword());
//        return newUser;
//    }

    @PutMapping("/users")
    public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws Exception
    {
        User reqUser=userService.findUserByJwt(jwt);

        User updatedUser=userService.updateUser(user,reqUser.getId());
        return updatedUser;

        }
  @PutMapping("/users/follow/{userId2}")
   public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws Exception
   {
       User reqUser=userService.findUserByJwt(jwt);

        User user=userService.followUser(reqUser.getId(),userId2);
        return user;
   }

   @GetMapping("/users/search")
   public List<User>searchUser(@RequestParam("query") String query){

        List<User> users=userService.searchUser(query);
        return users;
   }
  @GetMapping("/users/profile")
   public User getUserFromToken(@RequestHeader ("Authorization")String jwt) throws Exception
   {

      User user= userService.findUserByJwt(jwt);
      user.setPassword(null);

      return user;
   }




}

