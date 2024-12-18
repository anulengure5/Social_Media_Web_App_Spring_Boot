package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.Service.StoryService;
import com.anulengure5.websocialapp.Service.UserService;
import com.anulengure5.websocialapp.models.Story;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization")String jwt) throws Exception {

        User reqUser=userService.findUserByJwt(jwt);

        Story createdStory=storyService.createStory(story,reqUser);

        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId, @RequestHeader("Authorization")String jwt) throws Exception {

        User reqUser=userService.findUserByJwt(jwt);//checks if valid logged in user is requsting from protected API

        List<Story> stories=storyService.findStoryByUserId(userId);

        return stories;
    }


}
