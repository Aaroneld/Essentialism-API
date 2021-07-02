package com.merrifield.Essentialism.API.repositories;

import com.merrifield.Essentialism.API.models.JoinTableModels.UserValue;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValuesId;
import org.springframework.data.repository.CrudRepository;

public interface UserValueRepository extends CrudRepository<UserValue, UserValuesId> {
}
