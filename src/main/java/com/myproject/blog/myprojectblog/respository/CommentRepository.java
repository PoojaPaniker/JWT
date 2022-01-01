package com.myproject.blog.myprojectblog.respository;

import com.myproject.blog.myprojectblog.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
}
