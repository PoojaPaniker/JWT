package com.myproject.blog.myprojectblog.service.impl;

import com.myproject.blog.myprojectblog.dto.CommentDto;
import com.myproject.blog.myprojectblog.exception.ResourceNotFoundException;
import com.myproject.blog.myprojectblog.model.Comments;
import com.myproject.blog.myprojectblog.model.Post;
import com.myproject.blog.myprojectblog.respository.CommentRepository;
import com.myproject.blog.myprojectblog.respository.PostRepository;
import com.myproject.blog.myprojectblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
       Comments comments = mapToEntity(commentDto);
       Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
       comments.setPost(post);
       return mapToDto(commentRepository.save(comments));

    }

    private CommentDto mapToDto(Comments comments){
        CommentDto commentDto = modelMapper.map(comments,CommentDto.class);
        return commentDto;
    }
    private Comments mapToEntity(CommentDto commentDto){
        Comments comments1 = modelMapper.map(commentDto,Comments.class);
        return comments1;
    }
}
