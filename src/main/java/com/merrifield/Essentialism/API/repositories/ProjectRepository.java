package com.merrifield.Essentialism.API.repositories;

import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findByUserId(long id);
}
