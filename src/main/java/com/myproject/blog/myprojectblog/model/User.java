package com.myproject.blog.myprojectblog.model;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}),@UniqueConstraint(columnNames = {"username"})})
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "roleId"))
    private Set<Role> roles;
}
