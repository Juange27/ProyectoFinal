package com.proyectofinal.app.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectofinal.app.documents.Calificacion;

public interface CalificacionRepository extends MongoRepository<Calificacion, String>{
	List<Calificacion> findByEstudianteId(String id);

}
