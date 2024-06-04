package com.proyectofinal.app.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectofinal.app.documents.Estudiante;

public interface EstudianteRepository extends MongoRepository<Estudiante, String>{
	Estudiante findByCorreo(String correo);
}
