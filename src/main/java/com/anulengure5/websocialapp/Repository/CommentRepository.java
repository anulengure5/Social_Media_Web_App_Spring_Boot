package com.anulengure5.websocialapp.Repository;

import com.anulengure5.websocialapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {


}
