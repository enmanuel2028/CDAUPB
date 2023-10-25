package co.edu.upb.Operador;

import java.util.LinkedList;

public class Cita {
    public LinkedList<Estudiante> listaCitas = new LinkedList<>();
    public int numeroSemestre;
    public String nombresEstudiantes;
    public String apellidosEstudiantes;
    public int id;
    public String fecha;
    public String hora;
    public int numeroCitas;


    public Cita(){
        numeroCitas = 0;
    }

    public Cita(int numeroSemestre, String nombresEstudiantes, String apellidosEstudiantes, int id, String fecha,
            String hora, int numeroCitas) {
        this.numeroSemestre = numeroSemestre;
        this.nombresEstudiantes = nombresEstudiantes;
        this.apellidosEstudiantes = apellidosEstudiantes;
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroCitas = numeroCitas;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public LinkedList<Estudiante> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(LinkedList<Estudiante> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public String getNombresEstudiantes() {
        return nombresEstudiantes;
    }

    public void setNombresEstudiantes(String nombresEstudiantes) {
        this.nombresEstudiantes = nombresEstudiantes;
    }

    public String getApellidosEstudiantes() {
        return apellidosEstudiantes;
    }

    public void setApellidosEstudiantes(String apellidosEstudiantes) {
        this.apellidosEstudiantes = apellidosEstudiantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getNumeroCitas() {
        return numeroCitas;
    }

    public void setNumeroCitas(int numeroCitas) {
        this.numeroCitas = numeroCitas;
    }

    public void agendarCita(Estudiante nuevaCita){
        listaCitas.add(nuevaCita);
        numeroCitas++;
    }

    public void cancelarCita(int id){
        listaCitas.removeIf(cita -> String.valueOf(cita.getId()).equals(id));
        numeroCitas--;
    }
    
    public void imprimir(){
        for(Estudiante citas : listaCitas){
            System.out.println(citas);
        }
    }

    public String toString() {
        return "SEMESTRE: " + numeroSemestre + "\n" +
               "NOMBRES: " + nombresEstudiantes + "\n" +
               "APELLIDOS: " + apellidosEstudiantes + "\n" +
               "ID: " + id + "\n" +
               "FECHA: " + fecha + "\n" +
               "HORA: " + hora + "\n" + "\n";
    }
    
}
