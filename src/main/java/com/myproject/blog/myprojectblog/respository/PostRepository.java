package com.myproject.blog.myprojectblog.respository;

import com.myproject.blog.myprojectblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
