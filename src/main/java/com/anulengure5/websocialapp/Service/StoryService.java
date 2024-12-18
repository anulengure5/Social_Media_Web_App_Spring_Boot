package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.models.Story;
import com.anulengure5.websocialapp.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
