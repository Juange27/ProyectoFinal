package com.proyectofinal.app.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectofinal.app.documents.Materia;

public interface MateriaRepository extends MongoRepository<Materia, String> {
	List<Materia> findByNivel(int nivel);
}
