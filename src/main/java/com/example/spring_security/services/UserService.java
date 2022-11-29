package com.example.spring_security.services;

import com.example.spring_security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public UserDetails loadUserByUsername(String username);
}
