package com.proyectofinal.app.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofinal.app.documents.*;
import com.proyectofinal.app.respository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/materia")

public class MateriaControlador {

	@Autowired
	private MateriaRepository MateriaRepository;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMateria(@PathVariable("id") String id){
		try {
			MateriaRepository.deleteById(id);
			return new ResponseEntity<String>("Registro eliminado",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("")
	public ResponseEntity<?> guardarMateria(@RequestBody Materia Materia){
		try {
			Materia saveMateria = MateriaRepository.save(Materia);
			return new ResponseEntity<Materia>(saveMateria,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("")
	public ResponseEntity<?> listarTodasMateriaes(){
		try {
			List<Materia> Materiaes=MateriaRepository.findAll();
			return new ResponseEntity<List<Materia>>(Materiaes,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarUnaMateria(@PathVariable String id) {
	    try {
	        Optional<Materia> MateriaOptional = MateriaRepository.findById(id);
	        if (MateriaOptional.isPresent()) {
	            Materia Materia = MateriaOptional.get();
	            return ResponseEntity.ok(Materia);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateMateria(@RequestBody Materia Materia) {
	    try {
	        Optional<Materia> MateriaOptional = MateriaRepository.findById(Materia.getId());
	        if (MateriaOptional.isPresent()) {
	            Materia asoSave = MateriaRepository.save(Materia);
	            return ResponseEntity.ok(asoSave);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asociación no se encontró");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().toString());
	    }
	}
	

	

}
