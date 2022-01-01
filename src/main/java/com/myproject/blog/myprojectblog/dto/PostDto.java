package com.myproject.blog.myprojectblog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    @NotEmpty
    @Size(min = 2,message = "Post title should have atleast 2 characters")
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 10,message = "Post Desc should be min 10 characters")
    private String description;
    @NotNull
    @NotBlank
    private String content;
    private long id;
    private Set<CommentDto> commentDtoSet;
}
