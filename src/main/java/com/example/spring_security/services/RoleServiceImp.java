package com.example.spring_security.services;

import com.example.spring_security.entities.Role;
import com.example.spring_security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        Long id;
        for (Role role : roleRepository.findAll()) {
            if (role.getName() == name) {
                return role;
            }
        }
        return null;
    }

    @Override
    public List<Role> getRoleByNames(String[] names) {
        List<Role> roleList = new ArrayList<>();
        for (String name:names) {
            roleList.add(getRoleByName(name));
        }
        return roleList;
    }
}
