package com.proyectofinal.app.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectofinal.app.documents.Coordinador;

public interface CoordinadorRepository extends MongoRepository<Coordinador, String>{
	Coordinador findByCorreo(String correo);
}
