package com.myproject.blog.myprojectblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
private LocalTime timetsamp;
private String message;
private String desc;
}
