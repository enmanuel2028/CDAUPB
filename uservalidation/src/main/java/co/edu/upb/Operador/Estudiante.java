package co.edu.upb.Operador;

public class Estudiante {
    public int numeroSemestre;
    public String nombresEstudiante;
    public String apellidosEstudiantes;
    public int id;
    public String fecha;
    public String hora;
   

    public Estudiante(int numeroSemestre, String nombresEstudiante, String apellidosEstudiantes, int id, String fecha,
            String hora) {
        this.numeroSemestre = numeroSemestre;
        this.nombresEstudiante = nombresEstudiante;
        this.apellidosEstudiantes = apellidosEstudiantes;
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
    }


    public Estudiante(String semestre, int id2, String nombres, String apellidos, int id3, String fecha2,
            String hora2) {
    }


    public int getSemestre() {
        return numeroSemestre;
    }

    public void setnumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public String getNombresEstudiante() {
        return nombresEstudiante;
    }

    public void setNombresEstudiante(String nombresEstudiante) {
        this.nombresEstudiante = nombresEstudiante;
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

    public String toString() {
        return "SEMESTRE: " + numeroSemestre + "\n" +
               "NOMBRES: " + nombresEstudiante + "\n" +
               "APELLIDOS: " + apellidosEstudiantes + "\n" +
               "ID: " + id + "\n" +
               "FECHA: " + fecha + "\n" +
               "HORA: " + hora + "\n" + "\n";
    }
    
    
}
