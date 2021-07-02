package com.merrifield.Essentialism.API.repositories;
import com.merrifield.Essentialism.API.models.Value;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValueRepository extends CrudRepository<Value, Long> {
    Optional<Value> findValueById(long value);
}
