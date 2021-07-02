package com.merrifield.Essentialism.API.services.impl;

import com.merrifield.Essentialism.API.models.Role;
import com.merrifield.Essentialism.API.repositories.RoleRepository;
import com.merrifield.Essentialism.API.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public HashSet<Role> findAll(){
        return (HashSet<Role>) roleRepository.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Role with id %d not found", id)));
    }

    @Override
    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

}
