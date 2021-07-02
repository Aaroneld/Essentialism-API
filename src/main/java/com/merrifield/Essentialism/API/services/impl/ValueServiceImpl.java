package com.merrifield.Essentialism.API.services.impl;

import com.merrifield.Essentialism.API.models.Value;
import com.merrifield.Essentialism.API.repositories.ValueRepository;
import com.merrifield.Essentialism.API.services.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "valueService")
public class ValueServiceImpl implements ValueService {

    @Autowired
    ValueRepository valueRepository;

    @Override
    public List<Value> findAll() {
        return (ArrayList<Value>) valueRepository.findAll();
    }

    @Override
    public Value findValueById(long id) {
        return valueRepository.findValueById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Value with id %d not found", id)));
    }

    @Override
    public long addValue(String value) {
        return valueRepository.save(new Value(value)).getId();
    }

    @Override
    public void deleteValue(long id){
        valueRepository.delete(findValueById(id));
    }
}
