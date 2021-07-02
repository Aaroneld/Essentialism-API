package com.merrifield.Essentialism.API.services.impl;

import com.merrifield.Essentialism.API.models.*;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;
import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.ProjectModels.ProjectMinimum;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.repositories.ProjectRepository;
import com.merrifield.Essentialism.API.repositories.ProjectValueRepository;
import com.merrifield.Essentialism.API.repositories.UserRepository;
import com.merrifield.Essentialism.API.repositories.ValueRepository;
import com.merrifield.Essentialism.API.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.merrifield.Essentialism.API.services.AuthorizationUtilities.affectedResourceIsNotOwnedByLoggedInUser;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectsService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValueRepository valueRepository;

    @Autowired
    ProjectValueRepository projectValueRepository;

    @Override
    public List<Project> findAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public Project findProjectById(long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Project with id %s not found", id)));
    }

    @Override
    public List<Project> findProjectsOwnedByUser(long id) {
        return projectRepository.findByUserId(id);
    }

    @Override
    public long addProject(ProjectMinimum project) throws IllegalAccessException {
        User user = userRepository.findUserById(project.getUser()).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", project.getUser())));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, user.getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to add Project resource to user with id of %d", authentication.getName(), user.getId()));
        }

        Project projectEntity = new Project(project.getTitle(), user);

        if(project.getDescription() != null) {
            projectEntity.setDescription(project.getDescription());
        }

        return projectRepository.save(projectEntity).getId();
    }

    @Override
    public void deleteProject(long id) throws IllegalAccessException {

        Project projectToDelete = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Project with id %s not found", id)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, projectToDelete.getUser().getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to delete Project resource own by user with id of %d", authentication.getName(), projectToDelete.getUser().getId()));
        }

        projectRepository.delete(findProjectById(id));
    }

    @Override
    public ProjectValue findProjectValueById(ProjectValueId projectValueId) {
        return projectValueRepository.findById(projectValueId).orElseThrow(() -> new EntityNotFoundException(String.format("Value with id %s not found on project with id %s", projectValueId.getValue(), projectValueId.getProject())));
    }

    @Override
    public Set<String> addProjectValue(ProjectValueId projectValueId) throws IllegalAccessException {
        Project project = projectRepository.findById(projectValueId.getProject()).orElseThrow(() -> new EntityNotFoundException(String.format("Project with id %d not found", projectValueId.getProject())));
        Value value = valueRepository.findValueById(projectValueId.getValue()).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", projectValueId.getValue())));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, project.getUser().getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update Project resource owned by user with id of %d", authentication.getName(), project.getUser().getId()));
        }

        ProjectValue projectValue = new ProjectValue();
        projectValue.setProject(project);
        projectValue.setValue(value);
        project.getProjectValues().add(projectValue);
        projectRepository.save(project);

        return project.getProjectValues().stream().map((pv) -> projectValue.getValue().getValue()).collect(Collectors.toSet());
    }

    @Override
    public void deleteProjectValue(ProjectValueId projectValueId) throws IllegalAccessException {

        Project affectedProject = projectRepository.findById(projectValueId.getProject()).orElseThrow(() -> new EntityNotFoundException(String.format("Project with id %d not found", projectValueId.getProject())));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(affectedResourceIsNotOwnedByLoggedInUser(authentication, affectedProject.getUser().getUsername())){
            throw new IllegalAccessException(String.format("User %s is not authorized to update Project resource owned by user with id of %d", authentication.getName(), affectedProject.getUser().getId()));
        }

        projectValueRepository.delete(findProjectValueById(projectValueId));
    }
}
