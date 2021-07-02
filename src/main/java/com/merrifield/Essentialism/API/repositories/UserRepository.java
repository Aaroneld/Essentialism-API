package com.merrifield.Essentialism.API.repositories;

import com.merrifield.Essentialism.API.models.UserModels.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserById(long id);

    List<User> findByEmailContaining(String email);

    Optional<User> findByUsername(String username);
}
