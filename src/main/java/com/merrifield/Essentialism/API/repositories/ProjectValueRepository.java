package com.merrifield.Essentialism.API.repositories;

import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;
import org.springframework.data.repository.CrudRepository;

public interface ProjectValueRepository extends CrudRepository<ProjectValue, ProjectValueId> {
}
