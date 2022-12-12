package com.example.spring_security.repositories;

import com.example.spring_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct us from User us join fetch us.roles where us.username= :username")
    User findByUsername(String username);

    @Query("select distinct us from User us left join fetch us.roles")
    public List<User> findAll();
}
