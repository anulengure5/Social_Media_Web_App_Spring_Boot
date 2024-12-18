package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.ReelsRepository;
import com.anulengure5.websocialapp.models.Reels;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImplementation implements ReelsService{

    @Autowired
    ReelsRepository reelsRepository;

    @Autowired
    UserService userService;


    @Override
    public Reels createReels(Reels reels, User user) {

        Reels createReel=new Reels();

        createReel.setTitle(reels.getTitle());
        createReel.setVideo(reels.getVideo());
        createReel.setUser(user);

       return  reelsRepository.save(createReel);

    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {

        userService.findUserById(userId);

        return reelsRepository.findByUserId(userId);
    }
}
