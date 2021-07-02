package com.merrifield.Essentialism.API.controllers;

import com.merrifield.Essentialism.API.models.ProjectModels.ProjectMinimum;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;

import com.merrifield.Essentialism.API.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    ProjectsService projectsService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllProjects() {
        return new ResponseEntity<>(projectsService.findAllProjects(), HttpStatus.OK);
    }

    @GetMapping( value = "/{id}")
    public ResponseEntity<?> findProjectById(@PathVariable long id){
        return new ResponseEntity<>(projectsService.findProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> findProjectsByOwner(@PathVariable long userId){
        return new ResponseEntity<>(projectsService.findProjectsOwnedByUser(userId), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addProject(@RequestBody @Valid ProjectMinimum project) throws IllegalAccessException {
        return new ResponseEntity<>(projectsService.addProject(project), HttpStatus.CREATED);
    }

    @PostMapping(value = "/value", consumes = "application/json")
    public ResponseEntity<?> addProjectValue(@RequestBody @Valid ProjectValueId projectValueId) throws IllegalAccessException {
        return new ResponseEntity<>(projectsService.addProjectValue(projectValueId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json",  produces = "application/json")
    public ResponseEntity<?> deleteProject(@PathVariable long id) throws IllegalAccessException {
        projectsService.deleteProject(id);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/value", consumes = "application/json",  produces = "application/json")
    public ResponseEntity<?> deleteProjectValue(@RequestBody @Valid ProjectValueId projectValueId) throws IllegalAccessException {
        projectsService.deleteProjectValue(projectValueId);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }

}
