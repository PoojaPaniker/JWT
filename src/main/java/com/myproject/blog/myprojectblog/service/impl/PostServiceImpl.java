package com.myproject.blog.myprojectblog.service.impl;

import com.myproject.blog.myprojectblog.dto.PostDto;
import com.myproject.blog.myprojectblog.dto.PostResponseDto;
import com.myproject.blog.myprojectblog.exception.ResourceNotFoundException;
import com.myproject.blog.myprojectblog.model.Post;
import com.myproject.blog.myprojectblog.respository.PostRepository;
import com.myproject.blog.myprojectblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        Post postNew = postRepository.save(post);
        PostDto postDto1 = new PostDto();
        postDto1.setId(postNew.getId());
        postDto1.setContent(postNew.getContent());
        postDto1.setTitle(postNew.getTitle());
        postDto1.setDescription(postNew.getDescription());
        return postDto1;
    }

    @Override
    public PostResponseDto getAllPosts(int pageNo,int pagesize) {
        /*Pageable pageable = PageRequest.of(pageNo,pagesize);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto> postDtoList = postRepository.findAll(pageable).stream().
                map(e -> new PostDto(e.getTitle(),e.getDescription(),e.getContent(),e.getId())).collect(Collectors.toList());
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setPostDtoList(postDtoList);
        postResponseDto.setLastPage(postPage.isLast());
        postResponseDto.setPageNo(postPage.getNumber());
        postResponseDto.setPageSize(postPage.getSize());
        postResponseDto.setTotalElements(postPage.getTotalElements());
        postResponseDto.setTotalPages(postPage.getTotalPages());*/
        return new PostResponseDto();
    }
public PostDto getPostById(long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        post.getComments().forEach(System.out::println);
        return mapToDto(post);
}
    private PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post,PostDto.class);
        //postDto.getCommentDtoSet().forEach(System.out::println);
        return postDto;
    }

    private Post maoToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        return post;
    }
}
