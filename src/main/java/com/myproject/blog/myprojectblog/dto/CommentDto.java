package com.myproject.blog.myprojectblog.dto;

import lombok.*;

@Data
public class CommentDto {
    private long id;
    private String body;
    private String email;
    private String name;
}
