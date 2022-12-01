package com.example.spring_security.services;


import com.example.spring_security.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> allRoles();

    Role getRoleByName(String name);

    List<Role> getRoleByNames(String[] names);

}
