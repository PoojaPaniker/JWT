package com.myproject.blog.myprojectblog.service;

import com.myproject.blog.myprojectblog.dto.CommentDto;
import com.myproject.blog.myprojectblog.dto.PostDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,long postId);
}
