package com.anulengure5.websocialapp.Repository;


import com.anulengure5.websocialapp.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {

    public List<Story> findByUserId(Integer userId);
}
