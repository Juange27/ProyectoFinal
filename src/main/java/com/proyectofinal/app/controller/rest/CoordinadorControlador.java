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
@RequestMapping("api/coordinador")

public class CoordinadorControlador {

	@Autowired
	private CoordinadorRepository CoordinadorRepository;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCoordinador(@PathVariable("id") String id){
		try {
			CoordinadorRepository.deleteById(id);
			return new ResponseEntity<String>("Registro eliminado",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("")
	public ResponseEntity<?> guardarCoordinador(@RequestBody Coordinador Coordinador){
		try {
			Coordinador saveCoordinador = CoordinadorRepository.save(Coordinador);
			return new ResponseEntity<Coordinador>(saveCoordinador,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("")
	public ResponseEntity<?> listarTodasCoordinadores(){
		try {
			List<Coordinador> Coordinadores=CoordinadorRepository.findAll();
			return new ResponseEntity<List<Coordinador>>(Coordinadores,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listarUnaCoordinador(@PathVariable String id) {
	    try {
	        Optional<Coordinador> CoordinadorOptional = CoordinadorRepository.findById(id);
	        if (CoordinadorOptional.isPresent()) {
	            Coordinador Coordinador = CoordinadorOptional.get();
	            return ResponseEntity.ok(Coordinador);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateCoordinador(@RequestBody Coordinador Coordinador) {
	    try {
	        Optional<Coordinador> CoordinadorOptional = CoordinadorRepository.findById(Coordinador.getId());
	        if (CoordinadorOptional.isPresent()) {
	            Coordinador asoSave = CoordinadorRepository.save(Coordinador);
	            return ResponseEntity.ok(asoSave);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asociación no se encontró");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().toString());
	    }
	}
	

	

}
