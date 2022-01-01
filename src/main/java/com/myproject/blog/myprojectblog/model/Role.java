package com.myproject.blog.myprojectblog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Roles",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String name;
}
