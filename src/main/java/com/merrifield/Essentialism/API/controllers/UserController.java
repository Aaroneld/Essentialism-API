package com.merrifield.Essentialism.API.controllers;

import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValuesId;
import com.merrifield.Essentialism.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserById(@PathVariable long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findUserByEmailContaining(email), HttpStatus.OK);
    }

    @PostMapping(path = "/value", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserValue(@RequestBody @Valid UserValuesId userValuesId) throws IllegalAccessException {
        
        return new ResponseEntity<>(userService.addUserValue(userValuesId), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody @Valid  User user) throws IllegalAccessException {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable long id) throws IllegalAccessException {
        userService.deleteUser(id);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/value", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserValue(@RequestBody @Valid UserValuesId userValuesId) throws IllegalAccessException {
        userService.deleteUserValue(userValuesId);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }
}
