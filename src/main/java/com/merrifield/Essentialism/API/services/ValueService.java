package com.merrifield.Essentialism.API.services;

import com.merrifield.Essentialism.API.models.Value;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ValueService {
    List<Value> findAll();
    Value findValueById(long id);
    long addValue(String value);
    void deleteValue(long id);
}
