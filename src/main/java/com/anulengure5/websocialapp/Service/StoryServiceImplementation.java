package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.Repository.StoryRepository;
import com.anulengure5.websocialapp.Repository.UserRepository;
import com.anulengure5.websocialapp.models.Story;
import com.anulengure5.websocialapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {

        Story createdStory=new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {

       User user=userService.findUserById(userId);// to check if user exists

       return storyRepository.findByUserId(userId);

    }


}
