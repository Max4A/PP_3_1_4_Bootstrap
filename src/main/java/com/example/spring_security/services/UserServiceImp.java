package com.example.spring_security.services;

import com.example.spring_security.entities.User;
import com.example.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    @Lazy
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
//    @Query("select distinct us from User us join fetch us.roles where us.username= :username")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ##### теперь возвращаем userdetales через существующего юзера ###############
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().size();
        return user;
    }

    @Override
//    @Query("select distinct us from User us left join fetch us.roles")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(String username, User newUserData) {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
        user.setFirstname(newUserData.getFirstname());
        user.setLastname(newUserData.getLastname());
        user.setEmail(newUserData.getEmail());
        user.setRoles(newUserData.getRoles());
        userRepository.save(user);
    }

}
