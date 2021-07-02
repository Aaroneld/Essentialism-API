package com.merrifield.Essentialism.API.services;

import com.merrifield.Essentialism.API.models.Role;

import java.util.HashSet;

public interface RoleService {
    HashSet<Role> findAll();

    Role findById(long id);

    Role findByName(String name);

}
