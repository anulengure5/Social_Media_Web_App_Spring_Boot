package com.anulengure5.websocialapp.Service;

import com.anulengure5.websocialapp.models.Reels;
import com.anulengure5.websocialapp.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReels(Reels reels, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUserReels(Integer userId) throws Exception;
}
