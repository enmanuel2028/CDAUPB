package co.edu.upb.Operador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();

        estudiantes.add(new Estudiante(1, "John Jairo", "Perez Jimenes", 100451, "2023-10-25", "09:00 AM"));
        estudiantes.add(new Estudiante(7, "Camila Alejandra", "Sanchez Molina", 458451, "2023-11-06", "11:00 AM"));
        estudiantes.add(new Estudiante(3, "Luis Eduardo", "Gomez", 789654, "2023-10-28", "10:30 AM"));
        estudiantes.add(new Estudiante(4, "María Fernanda", "Lopez", 123456, "2023-11-10", "08:45 AM"));
        estudiantes.add(new Estudiante(5, "Andrés Felipe", "Hernández", 654321, "2023-10-30", "03:15 PM"));
        estudiantes.add(new Estudiante(4, "Carolina", "Martínez", 888888, "2023-11-15", "02:30 PM"));
        estudiantes.add(new Estudiante(6, "David", "Rodríguez", 777777, "2023-10-21", "01:20 PM"));
        estudiantes.add(new Estudiante(8, "Laura", "García", 666666, "2023-11-02", "09:30 AM"));
        estudiantes.add(new Estudiante(5, "Pedro", "Ramírez", 555555, "2023-10-18", "11:45 AM"));
        estudiantes.add(new Estudiante(3, "Alejandra", "Vargas", 444444, "2023-11-12", "10:00 AM"));

        // Crear un objeto Gson con formato legible (pretty printing)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(estudiantes);

        try (FileWriter writer = new FileWriter("estudiantes.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


