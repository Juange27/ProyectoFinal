package com.proyectofinal.app.controller.web;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.app.documents.*;
import com.proyectofinal.app.respository.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/coordinador/")
public class PerfilCoordinadorControlador {

	@Autowired
	private EstudianteRepository estRepo;
	@Autowired
	private CoordinadorRepository cooRepo;
	@Autowired
	private CalificacionRepository calRepo;

	@GetMapping("inicio-coordinador/{id}/")
	public String abrirInicioCoordinador(@PathVariable("id") String id, Model model) {
		Coordinador existeCoordinador = cooRepo.findById(id).orElseThrow(null);
		model.addAttribute("coordinador", existeCoordinador);
		model.addAttribute("estudiante", estRepo.findAll());
		return "coordinador/inicio-coordinador";
	}

	@GetMapping("agregar-estudiante/{id}/")
	public String getMethodName(@PathVariable("id") String id, Model model) {
		model.addAttribute("coordinador", cooRepo.findById(id).orElseThrow(null));
		model.addAttribute("estudiante", new Estudiante());
		return "coordinador/registrar-estudiante";
	}

	@PostMapping("guardar-estudiante/{id}/")
	public String guardarEstudiante(@PathVariable("id") String id, Model model, @ModelAttribute Estudiante estudiante) {
		if (estudiante.getId() == null || estudiante.getId().isEmpty()) {
			ObjectId ids = new ObjectId();
			estudiante.setId(ids.toHexString());
		}
		estRepo.save(estudiante);
		model.addAttribute("id", id);
		return "redirect:/coordinador/inicio-coordinador/{id}/";
	}

	@GetMapping("editar-estudiante/{coordinadorId}/{estudianteId}/")
	public String editarEstudiante(@PathVariable("coordinadorId") String coordinadorId,
			@PathVariable("estudianteId") String estudianteId, Model model) {
		Optional<Estudiante> estudianteEditado = estRepo.findById(estudianteId);
		if (estudianteEditado.isPresent()) {
			model.addAttribute("estudiante", estudianteEditado.get());
			model.addAttribute("coordinador", cooRepo.findById(coordinadorId).orElseThrow(null));
			return "coordinador/registrar-estudiante";
		} else {
			model.addAttribute("coordinador", cooRepo.findById(coordinadorId).orElseThrow(null));
			return "redirect:/coordinador/inicio-coordinador/{id}/";
		}
	}

	@GetMapping("eliminar-estudiante/{id}/{estudianteId}/")
	public String eliminarEstudiante(@PathVariable("id") String id, @PathVariable("estudianteId") String estudianteId,
			Model model) {
		estRepo.deleteById(estudianteId);
		model.addAttribute(id, "id");
		return "redirect:/coordinador/inicio-coordinador/{id}/";
	}

	@GetMapping("ver-notas/{coordinadorId}/{estudianteId}/")
	public String abrirListaNotas(@PathVariable("coordinadorId") String coordinadorId,
			@PathVariable("estudianteId") String estudianteId, Model model) {
		Estudiante existeEstudiante = estRepo.findById(estudianteId).orElseThrow(null);
		List<Calificacion> existenCalificaciones = calRepo.findByEstudianteId(existeEstudiante.getId());
		model.addAttribute("estudiante", existeEstudiante);
		model.addAttribute("calificacion", existenCalificaciones);
		model.addAttribute("coordinador", cooRepo.findById(coordinadorId).orElseThrow(null));
		return "coordinador/subir-notas";
	}

	@GetMapping("registrar-notas/{coordinadorId}/{estudianteId}/{calificacionId}/")
	public String abrirPantallaEdicionNotas(@PathVariable("coordinadorId") String coordinadorId,
			@PathVariable("estudianteId") String estudianteId, @PathVariable("calificacionId") String calificacionId,
			Model model) {
		Optional<Calificacion> calificacionEditada = calRepo.findById(calificacionId);
		if (calificacionEditada.isPresent()) {
			model.addAttribute("calificacion", calificacionEditada.get());
			model.addAttribute("estudiante", estRepo.findById(estudianteId).orElseThrow(null));
			model.addAttribute("coordinador", cooRepo.findById(coordinadorId).orElseThrow(null));
			return "coordinador/registrar-notas";
		} else {
			model.addAttribute("estudiante", estRepo.findById(estudianteId).orElseThrow(null));
			model.addAttribute("coordinador", cooRepo.findById(coordinadorId).orElseThrow(null));
			return "redirect:/coordinador/ver-notas/{coordinadorId}/{estudianteId}/";
		}
	}
	
	@PostMapping("guardar-notas/{coordinadorId}/{estudianteId}/")
	public String guardarEstudiante(@PathVariable("coordinadorId") String coordinadorId,
			@PathVariable("estudianteId") String estudianteId, Model model, @ModelAttribute Calificacion calificacion) {
		if (calificacion.getId() == null || calificacion.getId().isEmpty()) {
			ObjectId ids = new ObjectId();
			calificacion.setId(ids.toHexString());
		}
		calRepo.save(calificacion);
		model.addAttribute("coordinadorId", coordinadorId);
		model.addAttribute("estudianteId", estudianteId);
		return "redirect:/coordinador/ver-notas/{coordinadorId}/{estudianteId}/";
	}

}
