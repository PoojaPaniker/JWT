package com.myproject.blog.myprojectblog.service;

import com.myproject.blog.myprojectblog.dto.PostDto;
import com.myproject.blog.myprojectblog.dto.PostResponseDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponseDto getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(long postId);
}
