package com.proyectofinal.app.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "calificacion")
public class Calificacion {

    @Id
    private String id;
    @DBRef 
    private Estudiante estudiante;
    @DBRef
    private Materia materia;
    private double placementTest;
    private double foroActivity1;
    private double foroActivity2;
    private double speaking;
    private double writing;
    private double autoevaluacion;
    private double testFinal;
    private double notaFinal;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public double getPlacementTest() {
		return placementTest;
	}
	public void setPlacementTest(double placementTest) {
		this.placementTest = placementTest;
	}
	public double getForoActivity1() {
		return foroActivity1;
	}
	public void setForoActivity1(double foroActivity1) {
		this.foroActivity1 = foroActivity1;
	}
	public double getForoActivity2() {
		return foroActivity2;
	}
	public void setForoActivity2(double foroActivity2) {
		this.foroActivity2 = foroActivity2;
	}
	public double getSpeaking() {
		return speaking;
	}
	public void setSpeaking(double speaking) {
		this.speaking = speaking;
	}
	public double getWriting() {
		return writing;
	}
	public void setWriting(double writing) {
		this.writing = writing;
	}
	public double getAutoevaluacion() {
		return autoevaluacion;
	}
	public void setAutoevaluacion(double autoevaluacion) {
		this.autoevaluacion = autoevaluacion;
	}
	public double getTestFinal() {
		return testFinal;
	}
	public void setTestFinal(double testFinal) {
		this.testFinal = testFinal;
	}
	public double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
    
    
}
