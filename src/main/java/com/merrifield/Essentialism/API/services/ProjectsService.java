package com.merrifield.Essentialism.API.services;

import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.ProjectModels.ProjectMinimum;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;

import java.util.List;
import java.util.Set;

public interface ProjectsService {
    List<Project> findAllProjects();

    Project findProjectById(long id);

    List<Project> findProjectsOwnedByUser(long id);

    long addProject(ProjectMinimum newProject) throws IllegalAccessException;

    void deleteProject(long id) throws IllegalAccessException;

    ProjectValue findProjectValueById(ProjectValueId projectValueId);

    Set<String> addProjectValue(ProjectValueId projectValueId) throws IllegalAccessException;

    void deleteProjectValue(ProjectValueId projectValueId) throws IllegalAccessException;
}
