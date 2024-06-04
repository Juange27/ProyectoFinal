package com.proyectofinal.app.controller.web;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.app.documents.*;
import com.proyectofinal.app.respository.*;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/estudiante/")
public class PerfilEstudianteControlador {

	@Autowired
	private EstudianteRepository estRepo;
	@Autowired
	private CalificacionRepository calRepo;
	@Autowired
	private MateriaRepository matRepo;

	@GetMapping("matricular-materia/{id}/")
	public String abrirPantallaMatricula(@PathVariable("id") String id, Model model) {
	    Estudiante estudiante = estRepo.findById(id).orElseThrow(null);
	    List<Calificacion> calificaciones = calRepo.findByEstudianteId(estudiante.getId());

	    if (calificaciones.isEmpty()) {
	        List<Materia> materias = matRepo.findByNivel(1); 
	        model.addAttribute("estudiante", estudiante);
	        model.addAttribute("materia", materias);
	        model.addAttribute("calificacion", new Calificacion());
	        return "estudiante/registrar-materia";
	    }

	    model.addAttribute("estudiante", estudiante);
	    model.addAttribute("materia", matRepo.findAll());
	    model.addAttribute("calificacion", new Calificacion());
	    return "estudiante/registrar-materia";
	}
	
	@GetMapping("inicio-estudiante/{id}")
	public String abrirPantallaInicio(@PathVariable("id") String id, Model model) {
		Estudiante existeEstudiante = estRepo.findById(id).orElseThrow(null);
		List<Calificacion> existenCalificaciones = calRepo.findByEstudianteId(existeEstudiante.getId());
		model.addAttribute("calificacion", existenCalificaciones);
		model.addAttribute("estudiante", existeEstudiante);
		return "estudiante/inicio-estudiante";
	}
	
	
	@PostMapping("guardar-materia/{id}/")
	public String guardarMateria(@PathVariable("id") String id, Model model, @ModelAttribute Calificacion calificacion) {	
		
		//AQUÍ HALLAMOS AL ESTUDIANTE Y SUS CALIFICACIONES
		Estudiante existeEstudiante = estRepo.findById(id).orElseThrow(null);
		List<Calificacion> existenCalificaciones = calRepo.findByEstudianteId(existeEstudiante.getId());

		
		//AQUÍ LE DAMOS  UN ID A LA CALIFICACIÓN
	    if (calificacion.getId() == null || calificacion.getId().isEmpty()) {
	        ObjectId ids = new ObjectId();
	        calificacion.setId(ids.toHexString());
	    }
	    
	    //AQUÍ VALIDARA SÍ EL ESTUDIANTE PUEDE O NO MATRICULAR ESA MATERIA
        int nivelNuevaMateria = calificacion.getMateria().getNivel();
        for (Calificacion cal : existenCalificaciones) {
            int nivelExistente = cal.getMateria().getNivel();
            if (nivelNuevaMateria <= nivelExistente || nivelNuevaMateria > nivelExistente + 1) {
                model.addAttribute("error", "No puede matricularse en una materia de nivel igual o menor al existente, ni mayor a existente + 1.");
                model.addAttribute("id", id);
                return "redirect:/estudiante/matricular-materia/{id}/";  
            }
        }

	    
	   //AQUÍ GUARDA LA MATERIA
	    calRepo.save(calificacion);
		model.addAttribute("id", id);
	    return "redirect:/estudiante/inicio-estudiante/{id}";
	}
	

}
