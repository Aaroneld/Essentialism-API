package com.merrifield.Essentialism.API.services.impl;

import com.merrifield.Essentialism.API.models.*;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValuesId;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.repositories.ProjectRepository;
import com.merrifield.Essentialism.API.repositories.UserRepository;
import com.merrifield.Essentialism.API.repositories.UserValueRepository;
import com.merrifield.Essentialism.API.repositories.ValueRepository;
import com.merrifield.Essentialism.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.merrifield.Essentialism.API.services.AuthorizationUtilities.*;


@Service( value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ValueRepository valueRepository;

    @Autowired
    UserValueRepository userValueRepository;

    @Override
    public List<User> findAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", id)));
    }

    @Override
    public List<User> findUserByEmailContaining(String email) {
        return userRepository.findByEmailContaining(email);
    }

    @Transactional
    @Override
    public long addUser(User user) {
        user.setId(0);
        return userRepository.save(user).getId();
    }

    @Transactional
    @Override
    public long updateUser(User user, Long id) throws IllegalAccessException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userToChange = userRepository.findUserById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", id)));

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, userToChange.getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update user with id of %d", authentication.getName(), id));
        }

        if(user.getUsername() != null) {
            userToChange.setUsername(user.getUsername());
        }

        if(user.getFirstName() != null) {
            userToChange.setFirstName(user.getFirstName());
        }

        if(user.getLastName() != null) {
            userToChange.setLastName(user.getLastName());
        }

        if(user.getEmail() != null) {
            userToChange.setEmail(user.getEmail());
        }

        if(user.getPhone() != null) {
            userToChange.setPhone(user.getPhone());
        }

        return  userRepository.save(userToChange).getId();
    }

    @Override
    public void deleteUser(long id) throws IllegalAccessException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userToDelete = userRepository.findUserById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", id)));

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, userToDelete.getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update user with id of %d", authentication.getName(), id));
        }

        userRepository.delete(findUserById(id));
    }

    @Override
    @Transactional
    public Set<String> addUserValue(UserValuesId userValueId) throws IllegalAccessException {

        User user = userRepository.findUserById(userValueId.getUser()).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", userValueId.getUser())));
        Value value = valueRepository.findValueById(userValueId.getValue()).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", userValueId.getValue())));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, user.getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update user with id of %d", authentication.getName(), user.getId()));
        }

        UserValue userValue = new UserValue();
        userValue.setUser(user);
        userValue.setValue(value);
        user.getUserValues().add(userValue);
        userRepository.save(user);

        return user.getUserValues().stream().map(uv -> uv.getValue().getValue()).collect(Collectors.toSet());
    }

    @Override
    public UserValue findUserValueById(UserValuesId userValuesId){
        return userValueRepository.findById(userValuesId).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d  with value id %d not found", userValuesId.getUser(), userValuesId.getValue())));

    }

    @Override
    public void deleteUserValue(UserValuesId userValueId) throws IllegalAccessException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User affectedUser = userRepository.findUserById(userValueId.getUser()).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", userValueId.getValue())));

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, affectedUser.getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update user with id of %d", authentication.getName(), userValueId.getUser()));
        }


        userValueRepository.delete(findUserValueById(userValueId));
    }

}
