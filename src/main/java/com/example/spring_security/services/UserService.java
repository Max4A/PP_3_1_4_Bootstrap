package com.example.spring_security.services;

import com.example.spring_security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public UserDetails loadUserByUsername(String username);

    public List<User> findAll();


//    public User getUserById(Long id);

    public void saveUser(User user);

//    public void updateUser(Long id, User updatedUser);
//
//    public void deleteUser(Long id);
}
