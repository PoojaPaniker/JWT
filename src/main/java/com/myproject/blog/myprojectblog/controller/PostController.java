package com.myproject.blog.myprojectblog.controller;

import com.myproject.blog.myprojectblog.dto.CommentDto;
import com.myproject.blog.myprojectblog.dto.PostDto;
import com.myproject.blog.myprojectblog.dto.PostResponseDto;
import com.myproject.blog.myprojectblog.service.CommentService;
import com.myproject.blog.myprojectblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("{postId}")
    public PostDto getPostById(@PathVariable("postId") long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);


    }

    @GetMapping
    public PostResponseDto getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return postService.getAllPosts(pageNo, pageSize);
    }

    @PostMapping("/{post_id}/comments")
    public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto comm, @PathVariable(value = "post_id") long postId) {
        return new ResponseEntity<>(commentService.createComment(comm, postId), HttpStatus.CREATED);

    }
}
