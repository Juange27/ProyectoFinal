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
@RequestMapping("api/calificacion")

public class CalificacionControlador {

	@Autowired
	private CalificacionRepository CalificacionRepository;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCalificacion(@PathVariable("id") String id){
		try {
			CalificacionRepository.deleteById(id);
			return new ResponseEntity<String>("Registro eliminado",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("")
	public ResponseEntity<?> guardarCalificacion(@RequestBody Calificacion Calificacion){
		try {
			Calificacion saveCalificacion = CalificacionRepository.save(Calificacion);
			return new ResponseEntity<Calificacion>(saveCalificacion,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("")
	public ResponseEntity<?> listarTodasCalificaciones(){
		try {
			List<Calificacion> Calificaciones=CalificacionRepository.findAll();
			return new ResponseEntity<List<Calificacion>>(Calificaciones,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarUnaCalificacion(@PathVariable String id) {
	    try {
	        Optional<Calificacion> CalificacionOptional = CalificacionRepository.findById(id);
	        if (CalificacionOptional.isPresent()) {
	            Calificacion Calificacion = CalificacionOptional.get();
	            return ResponseEntity.ok(Calificacion);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateCalificacion(@RequestBody Calificacion Calificacion) {
	    try {
	        Optional<Calificacion> CalificacionOptional = CalificacionRepository.findById(Calificacion.getId());
	        if (CalificacionOptional.isPresent()) {
	            Calificacion asoSave = CalificacionRepository.save(Calificacion);
	            return ResponseEntity.ok(asoSave);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asociación no se encontró");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().toString());
	    }
	}
	

	

}
