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
@RequestMapping("api/estudiante")

public class EstudianteControlador {

	@Autowired
	private EstudianteRepository EstudianteRepository;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteEstudiante(@PathVariable("id") String id){
		try {
			EstudianteRepository.deleteById(id);
			return new ResponseEntity<String>("Registro eliminado",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("")
	public ResponseEntity<?> guardarEstudiante(@RequestBody Estudiante Estudiante){
		try {
			Estudiante saveEstudiante = EstudianteRepository.save(Estudiante);
			return new ResponseEntity<Estudiante>(saveEstudiante,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("")
	public ResponseEntity<?> listarTodasEstudiantees(){
		try {
			List<Estudiante> Estudiantees=EstudianteRepository.findAll();
			return new ResponseEntity<List<Estudiante>>(Estudiantees,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarUnaEstudiante(@PathVariable String id) {
	    try {
	        Optional<Estudiante> EstudianteOptional = EstudianteRepository.findById(id);
	        if (EstudianteOptional.isPresent()) {
	            Estudiante Estudiante = EstudianteOptional.get();
	            return ResponseEntity.ok(Estudiante);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateEstudiante(@RequestBody Estudiante Estudiante) {
	    try {
	        Optional<Estudiante> EstudianteOptional = EstudianteRepository.findById(Estudiante.getId());
	        if (EstudianteOptional.isPresent()) {
	            Estudiante asoSave = EstudianteRepository.save(Estudiante);
	            return ResponseEntity.ok(asoSave);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asociación no se encontró");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().toString());
	    }
	}
	

	

}
