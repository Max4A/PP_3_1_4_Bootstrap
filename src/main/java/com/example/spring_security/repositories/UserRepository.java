package com.example.spring_security.repositories;

import com.example.spring_security.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    @Override
//    <S extends User> List<S> findAll(Example<S> example);
}
