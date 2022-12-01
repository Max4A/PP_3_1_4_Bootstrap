package com.example.spring_security.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "user_roles",
            joinColumns = @JoinColumn (name="role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name="user_id", referencedColumnName = "id"))
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

    public void addUsers(User user) {
        if (users == null) {
            users = new ArrayList<User>();
        }
        users.add(user);
    }
}
