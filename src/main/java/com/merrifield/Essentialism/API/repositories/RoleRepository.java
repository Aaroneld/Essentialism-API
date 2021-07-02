package com.merrifield.Essentialism.API.repositories;

import com.merrifield.Essentialism.API.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
