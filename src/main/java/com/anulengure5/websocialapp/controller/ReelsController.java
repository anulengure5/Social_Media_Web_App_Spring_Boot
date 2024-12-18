package com.anulengure5.websocialapp.controller;

import com.anulengure5.websocialapp.models.Reels;
import com.anulengure5.websocialapp.models.User;
import com.anulengure5.websocialapp.Service.ReelsService;
import com.anulengure5.websocialapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization")String jwt) throws Exception {
         User reqUser=userService.findUserByJwt(jwt);

         Reels createdReel=reelsService.createReels(reel,reqUser);

         return createdReel;

    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels()  {

       List<Reels> reels=reelsService.findAllReels();

        return reels;

    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {

        List<Reels> reels=reelsService.findUserReels(userId);

        return reels;

    }



}
