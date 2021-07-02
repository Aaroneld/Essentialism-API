package com.merrifield.Essentialism.API.services;

import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValuesId;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(long id);
    List<User> findUserByEmailContaining(String email);
    long addUser(User user);
    long updateUser(User user, Long id) throws IllegalAccessException;
    void deleteUser(long id) throws IllegalAccessException;
    Set<String> addUserValue(UserValuesId userValue) throws IllegalAccessException;

    UserValue findUserValueById(UserValuesId userValuesId);

    void deleteUserValue(UserValuesId userValuesId) throws IllegalAccessException;
}
