package com.proyectofinal.app.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.app.documents.*;
import com.proyectofinal.app.respository.*;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class LoginControlador {

	@Autowired
	private EstudianteRepository estRepo;
	@Autowired
	private CoordinadorRepository cooRepo;
	@Autowired
	private CalificacionRepository calRepo;

	// PANTALLA DE INICIO

	@GetMapping
	public String inicioPantalla() {
		return "index";
	}

	// PANTALLAS DE LOGIN

	@GetMapping("login-estudiante/")
	public String logEstudiantePantalla() {
		return "estudiante/login-estudiante";
	}

	@GetMapping("login-coordinador/")
	public String logPantallaCoordinador() {
		return "coordinador/login-coordinador";
	}

	// FUNCIONALIDAD DEL LOGIN
	@PostMapping("iniciarsesion/estudiante/")
	public String postMethodName(@ModelAttribute Estudiante estudiante, Model model) {
		Estudiante existeEstudiante = estRepo.findByCorreo(estudiante.getCorreo());
		if (existeEstudiante != null && existeEstudiante.getContrasena().equals(estudiante.getContrasena())) {
			List<Calificacion> existenCalificaciones = calRepo.findByEstudianteId(existeEstudiante.getId());
			model.addAttribute("calificacion", existenCalificaciones);
			model.addAttribute("estudiante", existeEstudiante);
			return "estudiante/inicio-estudiante";
		} else {
			model.addAttribute("mensaje", "Error");
			return "estudiante/login-estudiante";
		}
	}
	
	@PostMapping("iniciarsesion/coordinador/")
	public String postMethodName(@ModelAttribute Coordinador coordinador, Model model) {
		Coordinador existeCoordinador = cooRepo.findByCorreo(coordinador.getCorreo());
		if (existeCoordinador != null && existeCoordinador.getContrasena().equals(coordinador.getContrasena())) {
			model.addAttribute("coordinador", existeCoordinador);
			model.addAttribute("estudiante", estRepo.findAll());
			return "coordinador/inicio-coordinador";
		} else {
			model.addAttribute("mensaje", "Error");
			return "coordinador/login-coordinador";
		}
	}

}
