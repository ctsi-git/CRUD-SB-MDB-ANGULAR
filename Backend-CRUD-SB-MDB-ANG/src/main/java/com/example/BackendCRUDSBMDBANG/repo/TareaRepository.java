package com.example.BackendCRUDSBMDBANG.repo;

import com.example.BackendCRUDSBMDBANG.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaRepository extends MongoRepository<Tarea, String> {
}
