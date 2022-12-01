package com.example.spring_security.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "user_roles",
            joinColumns = @JoinColumn (name="role_id"),
            inverseJoinColumns = @JoinColumn (name="user_id"))
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
